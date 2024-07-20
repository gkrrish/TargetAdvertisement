package com.advt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.advt.service.AdvertiserInvoicePDFService;

@RestController
public class AdvertiserInvoicePDFController {

    @Autowired
    private AdvertiserInvoicePDFService invoiceService;

    @GetMapping("/AdvertiserInvoiceBill")
    public ResponseEntity<byte[]> generateInvoice(@RequestParam String mobileNumber) {
        try {
            byte[] pdfData = invoiceService.generateInvoicePdf(mobileNumber);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
