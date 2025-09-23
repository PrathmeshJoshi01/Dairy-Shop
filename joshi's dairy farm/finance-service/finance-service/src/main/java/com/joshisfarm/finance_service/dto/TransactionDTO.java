package com.joshisfarm.finance_service.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

	private Long id;
    private String type;      
    private String category;   
    private Double amount;
    private String description;
    private LocalDateTime date;
    private String createdBy; 
}
