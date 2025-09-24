package com.joshisFarm.company_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.company_service.dto.HealthSummaryDTO;

@FeignClient(name = "health-service", url = "http://localhost:8086/health")
public interface HealthClient {

    // Example: health-service/health/company/{companyId}/summary
    @GetMapping("/company/{companyId}/summary")
    HealthSummaryDTO getHealthSummary(@PathVariable("companyId") Long companyId,
                                      @RequestHeader("Authorization") String token);
}