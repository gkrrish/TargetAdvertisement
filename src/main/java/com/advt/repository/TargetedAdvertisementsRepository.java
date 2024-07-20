package com.advt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advt.main.entity.TargetedAdvertisements;
import com.advt.response.AdvertiserInvoiceResponse;
@Repository
public interface TargetedAdvertisementsRepository extends JpaRepository<TargetedAdvertisements, Integer> {
	
	/**
	 * These many fields are not required for generating the Invoice report, later remove it unnecessary fields.
	 */
	@Query("SELECT new com.advt.response.AdvertiserInvoiceResponse(ad.advertiserId, ad.advertiserName, ad.advertiserMobileNumber, ta.targetAdvtId, ta.fileLocation, ta.createdDate, ta.paid, ta.paidAmount, ta.verifiedByAdmin, ta.adminName, ta.verifiedTimestamp, ta.verificationRemarks, ta.verifiedAllowToPublish, tap.advertisementId, tap.locationId, tap.mediaType, tap.fileLength, tap.fileSize, tap.userCount, tap.price, tap.startTime, tap.endTime, tap.advertisementType, tap.amount, tap.tariffOffer, msl.locationName, ta.batchId) " +
	           "FROM AdvertiserDetails ad " +
	           "JOIN ad.targetedAdvertisements ta " +
	           "JOIN ta.targetedAdvertisementPlan tap " +
	           "JOIN tap.masterStatewiseLocation msl " +
	           "WHERE ad.advertiserMobileNumber = :mobileNumber")
	List<AdvertiserInvoiceResponse> findInvoiceDataByMobileNumber(@Param("mobileNumber") String mobileNumber);

}
