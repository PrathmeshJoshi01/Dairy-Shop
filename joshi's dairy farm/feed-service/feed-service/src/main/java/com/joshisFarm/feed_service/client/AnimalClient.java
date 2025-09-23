package com.joshisFarm.feed_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", url = "http://localhost:8081/auth")
public interface AnimalClient {
	@GetMapping("/{id}/exists")
	boolean existsById(@PathVariable("id")Long id);
}
