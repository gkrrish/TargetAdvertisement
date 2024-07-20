package com.advt.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.advt.repository.BatchJobRepository;
import com.advt.repository.TargetedAdvertisementsRepository;
import com.advt.response.AdvertiserInvoiceResponse;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class AdvertiserInvoicePDFService {

    @Autowired
    private TargetedAdvertisementsRepository targetedAdvertisementsRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BatchJobRepository batchRepository;

    public List<AdvertiserInvoiceResponse> getInvoiceDataByMobileNumber(String mobileNumber) {
        return targetedAdvertisementsRepository.findInvoiceDataByMobileNumber(mobileNumber);
    }

    public byte[] generateInvoicePdf(String mobileNumber) throws IOException {
        //String poppinsMedium = resourceLoader.getResource("classpath:fonts/Poppins-Medium.ttf").getFile().getPath();
		String poppinsLight = resourceLoader.getResource("classpath:fonts/Poppins-Light.ttf").getFile().getPath();
        PdfFont customFont = PdfFontFactory.createFont(poppinsLight, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        List<AdvertiserInvoiceResponse> invoiceDataList = getInvoiceDataByMobileNumber(mobileNumber);

        if (invoiceDataList.isEmpty()) {
            throw new RuntimeException("No data found for the provided mobile number.");
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        // Add Title
        Paragraph title = new Paragraph("Invoice")
                .setFont(customFont)
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        doc.add(title);

        // Add User Details
        AdvertiserInvoiceResponse invoiceData = invoiceDataList.get(0);
        Paragraph userDetails = new Paragraph()
                .add("Advertiser ID: " + invoiceData.getAdvertiserId() + "\n")
                .add("Advertiser Name: " + invoiceData.getAdvertiserName() + "\n")
                .add("Mobile Number: " + invoiceData.getAdvertiserMobileNumber() + "\n")
                .add("Invoice Date: " + invoiceData.getCreatedDate() + "\n")
                .setFont(customFont)
                .setMarginBottom(20);
        doc.add(userDetails);

        // Add Invoice Table
        float[] columnWidths = {1, 5, 5, 3, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));
        table.addHeaderCell(new Cell().add(new Paragraph("Description")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));
        table.addHeaderCell(new Cell().add(new Paragraph("Location")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));
        table.addHeaderCell(new Cell().add(new Paragraph("Delivery Time")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));
        table.addHeaderCell(new Cell().add(new Paragraph("Quantity")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));
        table.addHeaderCell(new Cell().add(new Paragraph("Price")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(customFont));

        for (AdvertiserInvoiceResponse data : invoiceDataList) {
        	Optional<String> deliveryTimeByBatchId = batchRepository.findDeliveryTimeByBatchId(Long.valueOf(data.getBatchId()));
        	String DeliveryTime = "DEFAULT";
        	if(deliveryTimeByBatchId.isPresent()) {
        		DeliveryTime=deliveryTimeByBatchId.get();
        	}
        	
        	String specifileName = data.getFileLocation().substring(data.getFileLocation().lastIndexOf("\\") + 1);
            table.addCell(new Cell().add(new Paragraph(String.valueOf(data.getTargetAdvtId())).setFont(customFont)));
            table.addCell(new Cell().add(new Paragraph(specifileName).setFont(customFont)));
            table.addCell(new Cell().add(new Paragraph(data.getLocationName()).setFont(customFont)));
            table.addCell(new Cell().add(new Paragraph(DeliveryTime).setFont(customFont)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(data.getUserCount())).setFont(customFont)));
            table.addCell(new Cell().add(new Paragraph(String.format("₹%.2f", data.getPaidAmount().doubleValue())).setFont(customFont)));
        }

        doc.add(table);

        // Add Total
		double total = invoiceDataList.stream().mapToDouble(data -> data.getPaidAmount().doubleValue()).sum();
		Paragraph totalParagraph = new Paragraph("Total: ₹" + String.format("%.2f", total)).setFont(customFont)
				.setFontSize(14).setTextAlignment(TextAlignment.RIGHT).setMarginTop(20);
		doc.add(totalParagraph);

        doc.close();
        
        return baos.toByteArray();
    }
}
