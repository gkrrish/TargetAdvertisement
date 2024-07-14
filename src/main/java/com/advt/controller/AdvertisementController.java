package com.advt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advt.entity.TargetedAdvertisements;
import com.advt.repository.AdvertiserDetailsRepository;
import com.advt.repository.TargetedAdvertisementsRepository;
import com.advt.request.CreateAdvertisementRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

    @Autowired
    private TargetedAdvertisementsRepository targetedAdvertisementsRepository;

    @Autowired
    private AdvertiserDetailsRepository advertiserDetailsRepository;

    @PostMapping
    public ResponseEntity<String> createAdvertisement(@Valid @RequestBody CreateAdvertisementRequest request) {
        if (!advertiserDetailsRepository.existsById(request.getAdvertiserId())) {
            return ResponseEntity.badRequest().body("Advertiser not found.");
        }

        if (request.getStartTime().after(request.getEndTime())) {
            return ResponseEntity.badRequest().body("Start time must be before end time.");
        }

        TargetedAdvertisements advertisement = new TargetedAdvertisements();
        advertisement.setAdvertiserId(request.getAdvertiserId());
        advertisement.setLocationId(request.getLocationId());
        advertisement.setMediaType(request.getMediaType());
        advertisement.setFileSize(request.getFileSize());
        advertisement.setUserCount(request.getUserCount());
        advertisement.setPrice(request.getPrice());
        advertisement.setStartTime(request.getStartTime());
        advertisement.setEndTime(request.getEndTime());
        advertisement.setAdvertisementType(request.getAdvertisementType());
        advertisement.setAmount(request.getAmount());
        advertisement.setTariffOffer(request.getTariffOffer());

        targetedAdvertisementsRepository.save(advertisement);
        return ResponseEntity.ok("Advertisement created successfully.");
    }
}

