package com.joshisFarm.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.user_service.entity.User;
	
public interface UserRepository extends JpaRepository<User, Long>{
	List<User>findByCompanyId(Long companyId);
}
