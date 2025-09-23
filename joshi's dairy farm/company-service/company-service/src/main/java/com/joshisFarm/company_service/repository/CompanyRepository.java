package com.joshisFarm.company_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joshisFarm.company_service.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
}
