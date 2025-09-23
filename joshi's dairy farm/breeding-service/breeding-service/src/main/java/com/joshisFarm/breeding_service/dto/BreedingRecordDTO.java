package com.joshisFarm.breeding_service.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreedingRecordDTO {
	private Long id;
	private Long animalId;
	private String eventType;
	private LocalDate eventDate;
	private String notes;
}
