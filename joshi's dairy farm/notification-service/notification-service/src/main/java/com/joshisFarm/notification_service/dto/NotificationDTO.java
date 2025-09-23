package com.joshisFarm.notification_service.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationDTO {
    private String type; // EMAIL, SMS, APP
    private String recipient;
    private String message;
}
