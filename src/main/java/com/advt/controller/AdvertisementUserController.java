package com.advt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advt.request.WelcomeRequest;
import com.advt.service.AdvertisementUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AdvertisementUserController {

	@Autowired
	private AdvertisementUserService advertisementUserService;

	@PostMapping("/welcome-advertiser")
	public String handleHiMessage(@RequestBody WelcomeRequest welcomeRequest) {
		return advertisementUserService.processWelcomeMessage(welcomeRequest.getMobileNumber());
	}

}
