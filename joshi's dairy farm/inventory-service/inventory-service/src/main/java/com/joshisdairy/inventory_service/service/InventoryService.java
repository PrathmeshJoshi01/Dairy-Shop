package com.joshisdairy.inventory_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisdairy.inventory_service.client.AuthClient;
import com.joshisdairy.inventory_service.client.NotificationClient;
import com.joshisdairy.inventory_service.dto.InventoryItemDTO;
import com.joshisdairy.inventory_service.entity.InventoryItem;
import com.joshisdairy.inventory_service.repository.InventoryItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository repository;
    private final AuthClient authClient;
    private final NotificationClient notificationClient;

    public InventoryItemDTO addItem(InventoryItemDTO dto, String token) {
        authClient.validateToken(token);

        InventoryItem item = InventoryItem.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .unit(dto.getUnit())
                .costPerUnit(dto.getCostPerUnit())
                .threshold(dto.getThreshold())
                .build();

        item = repository.save(item);
        return mapToDTO(item);
    }

    public List<InventoryItemDTO> getAllItems(String token) {
        authClient.validateToken(token);
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void consumeStock(String name, Double qty, String token) {
        authClient.validateToken(token);

        InventoryItem item = repository.findAll().stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getQuantity() < qty) {
            throw new RuntimeException("Not enough stock for " + name);
        }

        item.setQuantity(item.getQuantity() - qty);
        repository.save(item);

        if (item.getQuantity() < item.getThreshold()) {
            notificationClient.sendNotification("Low stock alert: " + name + " is below threshold!");
        }
    }

    private InventoryItemDTO mapToDTO(InventoryItem item) {
        return InventoryItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .category(item.getCategory())
                .quantity(item.getQuantity())
                .unit(item.getUnit())
                .costPerUnit(item.getCostPerUnit())
                .threshold(item.getThreshold())
                .build();
    }
}