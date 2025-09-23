package com.joshisFarm.health_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.health_service.entity.HealthRecord;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
	List<HealthRecord>findByAnimalId(Long animalId);
}
