package com.joshisFarm.company_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.company_service.dto.MilkSummaryDTO;

@FeignClient(name = "milk-service", url = "http://localhost:8085/milk")
public interface MilkClient {

    // Example endpoint: milk-service/milk/company/{companyId}/summary
    @GetMapping("/company/{companyId}/summary")
    MilkSummaryDTO getMilkSummary(@PathVariable("companyId") Long companyId,
                                  @RequestHeader("Authorization") String token);
}