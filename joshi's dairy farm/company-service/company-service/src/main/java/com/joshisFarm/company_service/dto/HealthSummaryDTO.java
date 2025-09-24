package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthSummaryDTO {
    private Integer totalAnimals;
    private Integer healthyCount;
    private Integer sickCount;
}