package com.advt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advt.request.AdvertisementPlanRequest;
import com.advt.request.AdvertisementRequest;
import com.advt.request.AdvertiserDetailsRequest;
import com.advt.service.AdvertisementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {
    
    @Autowired
    private AdvertisementService advertisementService;
    
    @PostMapping("/create/advertiseragent")
    public ResponseEntity<String> addAdvertiser(@Valid @RequestBody AdvertiserDetailsRequest request) {
    	String response = advertisementService.createAdvertiser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/advertisementplan")
    public ResponseEntity<String> createAdvertisementPlan(@Valid @RequestBody AdvertisementPlanRequest request) {
        String response = advertisementService.createAdvertisementPlan(request);
        return ResponseEntity.ok(response); //add the exceptions thoroughly.
    }
    
   
    @PostMapping("/create/advertisement")
    public ResponseEntity<String> createAdvertisement(@RequestBody AdvertisementRequest request) {
        try {
            long batchId = advertisementService.getBatchId(request.getBatchTime());
            boolean isPaymentSuccess = true; // fetch from payment service
            
            System.out.println("Delivery Time : "+request.getBatchTime() +" Batch Id :"+batchId);
            
            if (isPaymentSuccess) {
                String isSuccess = advertisementService.createAdvertisement(request, (int) batchId);
                return ResponseEntity.ok(isSuccess);
            } else {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Payment failed.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    
  
}

