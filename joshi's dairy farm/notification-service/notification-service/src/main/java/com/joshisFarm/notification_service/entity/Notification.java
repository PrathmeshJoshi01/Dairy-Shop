package com.joshisFarm.notification_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // EMAIL, SMS, APP
    private String recipient;
    private String message;
    private LocalDateTime createdAt;
    private boolean sent;
}
