package com.advt.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.advt.repository.BatchJobRepository;
import com.advt.repository.TargetedAdvertisementsRepository;
import com.advt.response.AdvertiserInvoiceResponse;
import com.advt.service.AdvertiserInvoicePDFService;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AdvertiserInvoicePDFControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @MockBean
    private TargetedAdvertisementsRepository targetedAdvertisementsRepository;

    @MockBean
    private BatchJobRepository batchJobRepository;

    @InjectMocks
    private AdvertiserInvoicePDFService advertiserInvoicePDFService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testGenerateInvoice() throws IOException {
        // Mock data
        AdvertiserInvoiceResponse response1 = new AdvertiserInvoiceResponse(
            1, "Krishna ", "+919876543220", 6,
            "C:\\Users\\ Krishna\\Downloads\\test-newspapers\\AnnojigudaLocalAdvt.png",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Y", new BigDecimal(100), "Y", "KRISHNA-TEST",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Good", "Y", 1, 1, "image", 30, 2048, 100,
            new BigDecimal(100), Timestamp.valueOf("2024-07-15 13:09:50.67"), Timestamp.valueOf("2024-07-16 13:09:57.814"),
            "Promotional", new BigDecimal(90), "10%DISCOUNT", "Telangana_Hyderabad_Hyderabad", 1);
        
        AdvertiserInvoiceResponse response2 = new AdvertiserInvoiceResponse(
            1, "Krishna ", "+919876543220", 7,
            "C:\\Users\\ Krishna\\Downloads\\test-newspapers\\AnotherAdvt.png",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Y", new BigDecimal(150), "Y", "KRISHNA-TEST",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Good", "Y", 1, 1, "image", 30, 2048, 200,
            new BigDecimal(150), Timestamp.valueOf("2024-07-15 13:09:50.67"), Timestamp.valueOf("2024-07-16 13:09:57.814"),
            "Promotional", new BigDecimal(135), "10%DISCOUNT", "Telangana_Hyderabad_Hyderabad", 2);
        
        AdvertiserInvoiceResponse response3 = new AdvertiserInvoiceResponse(
            1, "Krishna ", "+919876543220", 8,
            "C:\\Users\\ Krishna\\Downloads\\test-newspapers\\ThirdAdvt.png",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Y", new BigDecimal(200), "Y", "KRISHNA-TEST",
            Timestamp.valueOf("2024-07-15 18:06:48.203"), "Good", "Y", 1, 1, "image", 30, 2048, 300,
            new BigDecimal(200), Timestamp.valueOf("2024-07-15 13:09:50.67"), Timestamp.valueOf("2024-07-16 13:09:57.814"),
            "Promotional", new BigDecimal(180), "10%DISCOUNT", "Telangana_Hyderabad_Hyderabad", 3);

        when(targetedAdvertisementsRepository.findInvoiceDataByMobileNumber(anyString()))
            .thenReturn(Arrays.asList(response1, response2, response3));

        when(batchJobRepository.findDeliveryTimeByBatchId(1L)).thenReturn(Optional.of("10:00 AM"));
        when(batchJobRepository.findDeliveryTimeByBatchId(2L)).thenReturn(Optional.of("11:00 AM"));
        when(batchJobRepository.findDeliveryTimeByBatchId(3L)).thenReturn(Optional.of("12:00 PM"));

        // Test API
        Response apiResponse = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("mobileNumber", "+919876543220")
            .get("/AdvertiserInvoiceBill");

        // Log the response for debugging
        System.out.println("Response Status Code: " + apiResponse.getStatusCode());

        apiResponse.then()
            .statusCode(HttpStatus.OK.value())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        byte[] pdfData = apiResponse.getBody().asByteArray();

        // Save the PDF file to src/test/resources/files
        File outputDir = new File("src/test/resources/files");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Create directory if it does not exist
        }
        File outputFile = new File(outputDir, response1.getAdvertiserMobileNumber()+".pdf");
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(pdfData);
        }

        System.out.println("PDF file saved to: \n");
        System.err.println(outputFile.getAbsolutePath()+"\n ");
        
    }
}
