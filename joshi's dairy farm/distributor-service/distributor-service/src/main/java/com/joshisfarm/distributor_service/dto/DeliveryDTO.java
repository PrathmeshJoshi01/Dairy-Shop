package com.joshisfarm.distributor_service.dto;

import java.time.LocalDate;

import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class DeliveryDTO {
    private Long id;
    private Long distributorId;
    private Double quantityLiters;
    private LocalDate deliveryDate;
    private String shift;
    private Double price;
    private boolean paid;
}
