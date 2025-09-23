package com.joshisfarm.distributor_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistributorDTO {
    private Long id;
    private String name;
    private String location;
    private String contact;
    private Double balance;
}
