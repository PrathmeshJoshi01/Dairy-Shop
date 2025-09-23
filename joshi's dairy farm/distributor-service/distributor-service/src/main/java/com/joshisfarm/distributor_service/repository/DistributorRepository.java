package com.joshisfarm.distributor_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisfarm.distributor_service.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Long>{

}
