package com.advt.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advt.entity.AdvertiserDetails;
import com.advt.entity.TargetedAdvertisementPlan;
import com.advt.entity.TargetedAdvertisements;
import com.advt.repository.AdvertiserDetailsRepository;
import com.advt.repository.TargetedAdvertisementPlanRepository;
import com.advt.repository.TargetedAdvertisementsRepository;
import com.advt.request.AdvertisementPlanRequest;
import com.advt.request.AdvertisementRequest;
import com.advt.request.AdvertiserDetailsRequest;

@Service
public class AdvertisementService {

	@Autowired
	private TargetedAdvertisementsRepository targetedAdvertisementsRepository;

	@Autowired
	private TargetedAdvertisementPlanRepository targetedAdvertisementPlanRepository;

	@Autowired
	private AdvertiserDetailsRepository advertiserDetailsRepository;

	public TargetedAdvertisements createAdvertisement(AdvertisementRequest request) {
		TargetedAdvertisements advertisement = new TargetedAdvertisements();
		advertisement.setFileLocation("C:\\Users\\Gaganam Krishna\\Downloads\\test-newspapers\\AnnojigudaLocalAdvt.png");//later get from user
		advertisement.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		advertisement.setPaid("Y");//get this from payment gateway
		advertisement.setPaidAmount(request.getPaidAmount());
		advertisement.setVerifiedByAdmin("Y");//get it from LDAP employee profile
		advertisement.setAdminName("KRISHNA-TEST");
		advertisement.setVerifiedTimestamp(new Timestamp(System.currentTimeMillis()));
		advertisement.setVerificationRemarks("Good");
		advertisement.setVerifiedAllowToPublish("Y");
		TargetedAdvertisementPlan plan=new TargetedAdvertisementPlan();
		plan.setAdvertisementId(request.getAdvertisementId());
		advertisement.setTargetedAdvertisementPlan(plan);
		// Set relationships if needed
		return targetedAdvertisementsRepository.save(advertisement);
	}

	@Transactional
	public String createAdvertisementPlan(AdvertisementPlanRequest request) {
	    if (request.getStartTime().after(request.getEndTime())) {
	        return "Start time must be before end time.";
	    }

	    TargetedAdvertisementPlan advertisement = new TargetedAdvertisementPlan();
	    advertisement.setLocationId(request.getLocationId());
	    advertisement.setMediaType(request.getMediaType());
	    advertisement.setFileSize(request.getFileSize());
	    advertisement.setFileLength(request.getFileLength());
	    advertisement.setUserCount(request.getUserCount());
	    advertisement.setPrice(request.getPrice());
	    advertisement.setStartTime(request.getStartTime());
	    advertisement.setEndTime(request.getEndTime());
	    advertisement.setAdvertisementType(request.getAdvertisementType());
	    advertisement.setAmount(request.getAmount());
	    advertisement.setTariffOffer(request.getTariffOffer());

	    targetedAdvertisementPlanRepository.save(advertisement);
	    
	    return "Advertisement created successfully.";
	}


	public String createAdvertiser(AdvertiserDetailsRequest request) {
		AdvertiserDetails advertiser = new AdvertiserDetails();
//		advertiser.setUserId(request.getUserId());//through back-end validation we need to add here
//		advertiser.setIsUser(request.getIsUser());
		advertiser.setAdvertiserName(request.getAdvertiserName());
		advertiser.setAdvertiserMobileNumber(request.getAdvertiserMobileNumber());
		advertiser.setContactDetails(request.getContactDetails());
		advertiser.setStatus(request.getStatus());

		advertiserDetailsRepository.save(advertiser);

		return "Advertiser added successfully.";
	}
}
