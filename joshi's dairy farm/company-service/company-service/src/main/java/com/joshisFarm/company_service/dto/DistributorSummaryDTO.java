package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistributorSummaryDTO {
    private Integer activeDistributors;
    private Double pendingPayments;
}