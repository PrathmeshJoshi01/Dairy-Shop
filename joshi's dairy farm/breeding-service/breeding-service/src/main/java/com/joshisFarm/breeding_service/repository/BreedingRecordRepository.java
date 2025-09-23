package com.joshisFarm.breeding_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.breeding_service.entity.BreedingRecord;

public interface BreedingRecordRepository extends JpaRepository<BreedingRecord, Long>{
	List<BreedingRecord>findByAnimalId(Long animalId);
}
