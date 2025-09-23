package com.joshisdairy.inventory_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemDTO {
	
	private Long id;
	private String name;
	private String category;
	private Double quantity;  
    private String unit;         
    private Double costPerUnit;
    private Double threshold;    
}
