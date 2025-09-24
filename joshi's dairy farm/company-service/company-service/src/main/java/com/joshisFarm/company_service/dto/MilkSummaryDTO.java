package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkSummaryDTO {
    private Double totalTodayLiters;
    private Double averagePerCow;
}