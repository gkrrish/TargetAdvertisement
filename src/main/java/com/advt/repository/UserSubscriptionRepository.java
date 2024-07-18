package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advt.entity.UserSubscription;
import com.advt.entity.UserSubscriptionId;


@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {

		@Query("SELECT COUNT(us) FROM UserSubscription us " +
	           "JOIN us.vendor v " +
	           "JOIN v.location l " +
	           "WHERE l.locationId = :locationId")
	    long countSubscribersByLocationId(@Param("locationId") Long locationId);//validate later with original data
}