package com.joshisFarm.feed_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8088/inventory")
public interface InventoryClient {
	@PostMapping("/consume")
	void consumeFeed(@RequestParam String feedType, @RequestParam Double questityKg);
}
