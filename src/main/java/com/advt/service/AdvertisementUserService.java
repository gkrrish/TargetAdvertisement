package com.advt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advt.entity.TargetedAdvertisementPlan;
import com.advt.repository.AdvertiserDetailsRepository;
import com.advt.repository.TargetedAdvertisementPlanRepository;
import com.advt.response.AdvertiserResponse;
import com.advt.response.ExistingAdvertiserResponse;
import com.advt.response.NewAdvertiserResponse;

@Service
public class AdvertisementUserService {

	@Autowired
	private AdvertiserDetailsRepository advertiserDetailsRepository;
	
	@Autowired
    private TargetedAdvertisementPlanRepository targetedAdvertisementPlanRepository;


	/**
	 * //this is for single Location, need to check StateLevel or District Level later, and if bulk orders case?
	 */
	@Transactional(readOnly = true)
	public AdvertiserResponse processWelcomeMessage(String mobileNumber) {
		
		List<String> previousLocationByMobileNumber = advertiserDetailsRepository.findPreviousLocationByMobileNumber(mobileNumber);
		if(previousLocationByMobileNumber==null || previousLocationByMobileNumber.isEmpty()) {
			
			NewAdvertiserResponse response=new NewAdvertiserResponse();
			response.setWelcomeMessage("Welcome to Now-For Targeted Message! your advertisement is going to connect with Millions of Peoples!");
			response.setGreatePlaceToAdvt("You gonna Get the 10% flat DISCOUNT on here... use -- NOW IS WOW -- copon Code");
			return response;
		}else {
			ExistingAdvertiserResponse response= new ExistingAdvertiserResponse();
			response.setWelcomeBack("Heartfully! Welcome Back, --NOW-- is wising you a wonderfull service. \n below text contains the StateName-District-Mandal");
			response.setYourRecentPrevioiusLocation(previousLocationByMobileNumber.toString());
			return response;
		}
	}
	
	public List<TargetedAdvertisementPlan> getAvailablePlansByLocationId(Integer locationId) {
        return targetedAdvertisementPlanRepository.findAvailablePlansByLocationId(locationId);
    }

}
