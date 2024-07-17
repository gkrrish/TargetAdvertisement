package com.advt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advt.entity.StatewiseLocation;

@Repository
public interface StatewiseLocationRepository extends JpaRepository<StatewiseLocation, Long> {

	Optional<StatewiseLocation> findByMandal_MandalId(Long mandalId);
}
