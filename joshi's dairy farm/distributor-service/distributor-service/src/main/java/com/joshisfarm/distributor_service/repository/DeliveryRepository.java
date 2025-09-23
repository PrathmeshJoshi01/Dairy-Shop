package com.joshisfarm.distributor_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisfarm.distributor_service.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findByDistributorId(Long distributorId);
}
