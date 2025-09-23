package com.joshisFarm.notification_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joshisFarm.notification_service.dto.NotificationDTO;
import com.joshisFarm.notification_service.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> send(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.sendNotification(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> all() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
}
