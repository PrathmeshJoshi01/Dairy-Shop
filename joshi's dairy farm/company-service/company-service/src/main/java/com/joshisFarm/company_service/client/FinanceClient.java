package com.joshisFarm.company_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.company_service.dto.FinanceSummaryDTO;

@FeignClient(name = "finance-service", url = "http://localhost:8090/finance")
public interface FinanceClient {

    // Example endpoint: finance-service/finance/summary/{companyId}
    @GetMapping("/summary/{companyId}")
    FinanceSummaryDTO getFinanceSummary(@PathVariable("companyId") Long companyId,
                                        @RequestHeader("Authorization") String token);
}

//NOTE: If Finance Service doesn't yet expose /summary/{companyId}, you'll need
//to add that endpoint in Finance Service. 
//The CompanyService uses this endpoint for aggregation.