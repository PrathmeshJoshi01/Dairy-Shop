package com.joshisdairy.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         
    private String category;     
    private Double quantity;     
    private String unit;         
    private Double costPerUnit;
    private Double threshold;    
}