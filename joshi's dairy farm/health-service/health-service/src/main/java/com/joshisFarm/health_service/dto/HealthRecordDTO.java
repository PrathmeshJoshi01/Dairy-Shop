package com.joshisFarm.health_service.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthRecordDTO {

	private Long id;
	private Long animalId;
	private String diagnosis;
	private String medication;
	private LocalDate visitDate;
	private String vetName;
	private LocalDate nextVisit;
}
