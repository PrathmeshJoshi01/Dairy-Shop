package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    private CompanyDTO company;
    private FinanceSummaryDTO finance;
    private MilkSummaryDTO milk;
    private UserSummaryDTO users;
    private DistributorSummaryDTO distributors;
    private HealthSummaryDTO health;
}