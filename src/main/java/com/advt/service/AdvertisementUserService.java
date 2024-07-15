package com.advt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advt.entity.AdvertiserDetails;
import com.advt.repository.AdvertiserDetailsRepository;

@Service
public class AdvertisementUserService {

	@Autowired
	private AdvertiserDetailsRepository advertiserDetailsRepository;

	public String processWelcomeMessage(String mobileNumber) {
		Optional<AdvertiserDetails> advertiser = advertiserDetailsRepository.findByAdvertiserMobileNumber(mobileNumber);

		return advertiser.isPresent() ? "Welcome back!" : "Welcome!";
	}

}
