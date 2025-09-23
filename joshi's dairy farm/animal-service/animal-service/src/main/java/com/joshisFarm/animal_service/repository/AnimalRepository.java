package com.joshisFarm.animal_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.animal_service.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	List<Animal>findByCompanyId(Long companyId);
}
