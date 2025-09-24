package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceSummaryDTO {
    private Double totalIncome;
    private Double totalExpense;
    private Double profit;
}