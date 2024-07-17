package com.advt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.advt.request.WelcomeRequest;
import com.advt.response.AdvertiserResponse;
import com.advt.service.AdvertisementUserService;

@RestController
public class AdvertisementUserController {

	@Autowired
	private AdvertisementUserService advertisementUserService;

	@PostMapping("/welcome-advertiser")
	public AdvertiserResponse handleHiMessage(@RequestBody WelcomeRequest welcomeRequest) {
		return advertisementUserService.processWelcomeMessage(welcomeRequest.getMobileNumber());
	}

}
