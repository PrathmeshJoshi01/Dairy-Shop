package com.joshisFarm.milk_service.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkDTO {
	private Long id;
    private Long animalId;
    private Long companyId;
    private Double quantity;
    private Double fatPercent;
    private Double snfPercent;
    private String shift;
    private LocalDate date;
    private Long recordedBy;
}
