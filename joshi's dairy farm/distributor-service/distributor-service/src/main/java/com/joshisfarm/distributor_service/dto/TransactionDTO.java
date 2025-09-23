package com.joshisfarm.distributor_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
	private Long id;
    private String type;       // INCOME or EXPENSE
    private String category;   // MILK, FEED, MEDICINE, SALARY, EQUIPMENT
    private Double amount;
    private String description;
    private LocalDateTime date;
    private String createdBy;
}
