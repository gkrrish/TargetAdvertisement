package com.advt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advt.entity.TargetedAdvertisementPlan;

@Repository
public interface TargetedAdvertisementPlanRepository extends JpaRepository<TargetedAdvertisementPlan, Integer> {
	
		@Query("SELECT tap FROM TargetedAdvertisementPlan tap " +
	           "JOIN tap.masterStatewiseLocation sl " +
	           "WHERE sl.locationId = :locationId")
	    List<TargetedAdvertisementPlan> findAvailablePlansByLocationId(@Param("locationId") Integer locationId);

}
