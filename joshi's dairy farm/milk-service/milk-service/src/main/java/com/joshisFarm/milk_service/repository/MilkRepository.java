package com.joshisFarm.milk_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.milk_service.entity.MilkLog;

public interface MilkRepository extends JpaRepository<MilkLog, Long>{
	List<MilkLog>findByAnimalId(Long animalId);
	List<MilkLog>findByCompanyIdAndDate(Long companyId, LocalDate date);
}
