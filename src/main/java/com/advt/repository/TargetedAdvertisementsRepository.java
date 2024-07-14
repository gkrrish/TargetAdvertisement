package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advt.entity.TargetedAdvertisements;

@Repository
public interface TargetedAdvertisementsRepository extends JpaRepository<TargetedAdvertisements, Integer> {
}
