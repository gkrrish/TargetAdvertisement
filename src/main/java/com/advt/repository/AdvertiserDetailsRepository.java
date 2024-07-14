package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advt.entity.AdvertiserDetails;

@Repository
public interface AdvertiserDetailsRepository extends JpaRepository<AdvertiserDetails, Integer> {
}
