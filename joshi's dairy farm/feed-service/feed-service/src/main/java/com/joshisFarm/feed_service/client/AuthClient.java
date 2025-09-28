package com.joshisFarm.feed_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.feed_service.dto.UserResponseDTO;

@FeignClient(name = "auth-service",contextId = "feedAuthClient" ,path = "/auth" )
public interface AuthClient {
	@GetMapping("/validate")
	UserResponseDTO validateToken(@RequestHeader("Authorization")String token);
}
