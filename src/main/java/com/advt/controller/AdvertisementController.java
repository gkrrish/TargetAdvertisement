package com.advt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advt.entity.TargetedAdvertisements;
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

    @PostMapping("/create/advertisementplan")
    public ResponseEntity<String> createAdvertisementPlan(@Valid @RequestBody AdvertisementPlanRequest request) {
        String response = advertisementService.createAdvertisementPlan(request);
        return ResponseEntity.ok(response);
    }
    
   
    @PostMapping("/create/advertisement")
    public TargetedAdvertisements createAdvertisement(@RequestBody AdvertisementRequest request) {
        return advertisementService.createAdvertisement(request);
    }
    
    @PostMapping("/create/advertiseragent")
    public ResponseEntity<String> addAdvertiser(@Valid @RequestBody AdvertiserDetailsRequest request) {
    	String response = advertisementService.createAdvertiser(request);
        return ResponseEntity.ok(response);
    }
}

