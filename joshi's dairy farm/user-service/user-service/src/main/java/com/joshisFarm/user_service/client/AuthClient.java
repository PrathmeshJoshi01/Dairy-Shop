package com.joshisFarm.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.joshisFarm.user_service.dto.UserResponseDTO;

@FeignClient(name = "auth-service", url = "http://localhost:8081/auth")
public interface AuthClient {
	
	@GetMapping("/validate")
	UserResponseDTO validateToken(@RequestHeader("Authorization")String token);
}
