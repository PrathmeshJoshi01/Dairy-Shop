package com.joshisFarm.company_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
	private Long id;
    private String name;
    private String owner;
    private String location;
    private Double productionRate;
    private Double earning;
    private Integer workerCount;
    private Double companyArea;
}
