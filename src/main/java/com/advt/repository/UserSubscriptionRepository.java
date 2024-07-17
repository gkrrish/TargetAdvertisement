package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advt.entity.UserSubscription;
import com.advt.entity.UserSubscriptionId;


@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {

	@Query("SELECT COUNT(uns) FROM UserNewspaperSubscription uns " +
	           "JOIN Vendors v ON uns.newspaperMasterId = v.newspaperMasterId " +
	           "JOIN MasterStatewiseLocations msl ON v.locationId = msl.locationId " +
	           "WHERE msl.locationId = :locationId")
	    Long countSubscribersByLocationId(@Param("locationId") Integer locationId);
	
	
}