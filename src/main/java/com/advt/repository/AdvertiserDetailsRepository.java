package com.advt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advt.entity.AdvertiserDetails;

@Repository
public interface AdvertiserDetailsRepository extends JpaRepository<AdvertiserDetails, Integer> {

	@Query("SELECT sl.locationName FROM AdvertiserDetails ad " +
		       "JOIN ad.targetedAdvertisements ta " +
		       "JOIN ta.targetedAdvertisementPlan tap " +
		       "JOIN tap.masterStatewiseLocation sl " +
		       "WHERE ad.advertiserMobileNumber = :mobileNumber " +
		       "ORDER BY ta.createdDate DESC")
		List<String> findPreviousLocationByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
