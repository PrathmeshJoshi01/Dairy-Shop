package com.joshisfarm.distributor_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisfarm.distributor_service.dto.TransactionDTO;

@FeignClient(name = "finance-service", url = "http://localhost:8090/finance")
public interface FinanceClient {
    @PostMapping
    void addTransaction(@RequestBody TransactionDTO dto, @RequestHeader("Authorization") String token);
}