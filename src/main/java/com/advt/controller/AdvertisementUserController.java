package com.advt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
		return advertisementUserService.getAvailablePlansByLocationId(locationId);
	}
	
	@GetMapping("/subscribers/{locationId}")
	public Long getTotalSubscribersByLocationId(@PathVariable Integer locationId) {
		return advertisementUserService.getTotalSubscribersByLocationId(locationId);
	}
	
}
