package com.joshisdairy.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisdairy.inventory_service.entity.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
