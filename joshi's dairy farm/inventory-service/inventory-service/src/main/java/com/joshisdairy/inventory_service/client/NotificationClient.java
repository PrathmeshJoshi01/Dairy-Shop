package com.joshisdairy.inventory_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8086/notifications")
public interface NotificationClient {
    @PostMapping
    void sendNotification(@RequestBody String message);
}