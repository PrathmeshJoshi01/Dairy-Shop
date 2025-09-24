package com.joshisFarm.company_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.company_service.dto.DistributorSummaryDTO;

@FeignClient(name = "distributor-service", url = "http://localhost:8091/distributors")
public interface DistributorClient {

    // Example: distributor-service/distributors/company/{companyId}/summary
    @GetMapping("/company/{companyId}/summary")
    DistributorSummaryDTO getDistributorSummary(@PathVariable("companyId") Long companyId,
                                                @RequestHeader("Authorization") String token);
}