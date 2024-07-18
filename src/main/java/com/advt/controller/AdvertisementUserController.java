package com.advt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.advt.entity.TargetedAdvertisementPlan;
import com.advt.request.WelcomeRequest;
import com.advt.response.AdvertiserResponse;
import com.advt.service.AdvertisementUserService;
import com.advt.service.ExternalServiceCalls;

@RestController
public class AdvertisementUserController {

	@Autowired
	private AdvertisementUserService advertisementUserService;
	@Autowired
	private ExternalServiceCalls externalServiceCalls;


	@PostMapping("/welcome-advertiser")
	public AdvertiserResponse handleHiMessage(@RequestBody WelcomeRequest welcomeRequest) {
		return advertisementUserService.processWelcomeMessage(welcomeRequest.getMobileNumber());
	}
	
	
	@GetMapping("/states/{mobileNumber}")
    public List<String> getStates(@PathVariable String mobileNumber) {
        return externalServiceCalls.getStates(mobileNumber);
    }

    @GetMapping("/districts/{mobileNumber}/{stateName}")
    public List<String> getDistricts(@PathVariable String mobileNumber, @PathVariable String stateName) {
        return externalServiceCalls.getDistricts(mobileNumber, stateName);
    }

    @GetMapping("/mandals/{mobileNumber}/{districtName}")
    public List<String> getMandals(@PathVariable String mobileNumber, @PathVariable String districtName) {
        return externalServiceCalls.getMandals(mobileNumber, districtName);
    }
	

	@GetMapping("/available-plans/{locationId}")
	public List<TargetedAdvertisementPlan> getAvailablePlansByLocationId(@PathVariable Integer locationId) {
		//if plan not available then how to handle? need to send the details to admin and default plans should be shown right?
		//for that think after this
		return advertisementUserService.getAvailablePlansByLocationId(locationId);
	}
	
	@GetMapping("/subscribers/{locationId}")
	public Long getTotalSubscribersByLocationId(@PathVariable Integer locationId) {
		return advertisementUserService.getTotalSubscribersByLocationId(locationId);
	}
	
	@GetMapping("/batchids/{timePeriod}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getBatchIds(@PathVariable String timePeriod) {
		 return externalServiceCalls.getDeliveryTimesByTimePeriod(timePeriod);
    }
	
}
