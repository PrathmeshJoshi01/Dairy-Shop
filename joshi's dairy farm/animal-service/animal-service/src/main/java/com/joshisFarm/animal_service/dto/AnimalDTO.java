package com.joshisFarm.animal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {
	private Long id;
    private String name;
    private String breed;
    private Double weight;
    private Double height;
    private Double dailyMilkProduction;
    private Integer age;
    private String healthStatus;
    private String cycleStatus;
    private Long companyId;
}
