package com.advt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advt.entity.BatchJob;

@Repository
public interface BatchJobRepository extends JpaRepository<BatchJob, Long> {

	@Query("SELECT bj.deliveryTime FROM BatchJob bj")
	List<String> findAllDeliveryTimes();

	Optional<BatchJob> findByDeliveryTime(String deliveryTime);

	@Query("SELECT bj.deliveryTime FROM BatchJob bj WHERE bj.batchId BETWEEN :startId AND :endId")
	List<String> findDeliveryTimesInRange(Long startId, Long endId);

	@Query("SELECT bj.deliveryTime FROM BatchJob bj WHERE bj.batchId = :batchId")
	Optional<String> findDeliveryTimeByBatchId(Long batchId);

}