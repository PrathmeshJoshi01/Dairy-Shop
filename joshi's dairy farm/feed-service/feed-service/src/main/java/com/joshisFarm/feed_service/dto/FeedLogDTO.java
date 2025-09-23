package com.joshisFarm.feed_service.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedLogDTO {
	 private Long id;
	 private Long animalId;
	 private String feedType; 
	 private Double quantityKg;
	 private LocalDateTime feedTime;
	 private Double cost; 
	 private String recordedBy; 
}
