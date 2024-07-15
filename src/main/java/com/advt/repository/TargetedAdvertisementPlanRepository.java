package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advt.entity.TargetedAdvertisementPlan;

@Repository
public interface TargetedAdvertisementPlanRepository extends JpaRepository<TargetedAdvertisementPlan, Integer> {
}
