package com.joshisFarm.company_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.company_service.dto.UserSummaryDTO;

@FeignClient(name = "user-service", url = "http://localhost:8083/users")
public interface UserClient {

    // Example: user-service/users/company/{companyId}/summary
    @GetMapping("/company/{companyId}/summary")
    UserSummaryDTO getUserSummary(@PathVariable("companyId") Long companyId,
                                  @RequestHeader("Authorization") String token);
}