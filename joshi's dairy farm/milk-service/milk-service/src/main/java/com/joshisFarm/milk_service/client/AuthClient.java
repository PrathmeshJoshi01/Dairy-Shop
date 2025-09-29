package com.joshisFarm.milk_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.milk_service.dto.UserResponseDTO;

@FeignClient(name = "auth-service",contextId = "milkAuthClient",path = "/auth"  )
public interface AuthClient {

    @GetMapping("/validate")
    UserResponseDTO validateToken(@RequestHeader("Authorization") String token);
}