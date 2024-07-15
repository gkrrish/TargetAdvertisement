package com.advt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.advt.entity.TargetedAdvertisements;

public interface TargetedAdvertisementsRepository extends JpaRepository<TargetedAdvertisements, Integer> {
}
