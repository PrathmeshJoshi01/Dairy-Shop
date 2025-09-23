package com.joshisfarm.finance_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisfarm.finance_service.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
