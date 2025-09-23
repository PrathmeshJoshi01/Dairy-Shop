package com.joshisFarm.breeding_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "animal-service", url = "http://localhost:8084/animals")
public interface AnimalClient {
    @GetMapping("/{id}/exists")
    boolean existsById(@PathVariable("id") Long id);
}