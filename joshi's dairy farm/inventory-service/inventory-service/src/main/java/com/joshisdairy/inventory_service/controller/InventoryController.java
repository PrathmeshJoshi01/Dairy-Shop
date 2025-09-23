package com.joshisdairy.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joshisdairy.inventory_service.dto.InventoryItemDTO;
import com.joshisdairy.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryItemDTO> addItem(@RequestBody InventoryItemDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(inventoryService.addItem(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getAllItems(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(inventoryService.getAllItems(token));
    }

    @PostMapping("/consume")
    public ResponseEntity<String> consumeStock(@RequestParam String name,
                                               @RequestParam Double qty,
                                               @RequestHeader("Authorization") String token) {
        inventoryService.consumeStock(name, qty, token);
        return ResponseEntity.ok("Stock consumed successfully");
    }
}