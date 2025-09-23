package com.joshisFarm.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.user_service.entity.Attendence;

public interface AttendanceRepository extends JpaRepository<Attendence, Long>{
	List<Attendence> findByUserId(Long userId);
}
