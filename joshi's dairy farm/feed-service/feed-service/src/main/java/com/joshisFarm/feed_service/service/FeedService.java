package com.joshisFarm.feed_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.feed_service.client.AnimalClient;
import com.joshisFarm.feed_service.client.AuthClient;
import com.joshisFarm.feed_service.client.InventoryClient;
import com.joshisFarm.feed_service.client.NotificationClient;
import com.joshisFarm.feed_service.dto.FeedLogDTO;
import com.joshisFarm.feed_service.dto.UserResponseDTO;
import com.joshisFarm.feed_service.entity.FeedLog;
import com.joshisFarm.feed_service.repository.FeedLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedLogRepository feedLogRepository;
    private final AuthClient authClient;
    private final AnimalClient animalClient;
    private final InventoryClient inventoryClient;
    private final NotificationClient notificationClient;

    public FeedLogDTO logFeeding(FeedLogDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!animalClient.existsById(dto.getAnimalId())) {
            throw new RuntimeException("Animal not found.");
        }

        // Consume stock from inventory
        inventoryClient.consumeFeed(dto.getFeedType(), dto.getQuantityKg());

        FeedLog log = FeedLog.builder()
                .animalId(dto.getAnimalId())
                .feedType(dto.getFeedType())
                .quantityKg(dto.getQuantityKg())
                .feedTime(dto.getFeedTime())
                .cost(dto.getCost())
                .recordedBy(authUser.getUsername())
                .build();

        log = feedLogRepository.save(log);

        // send notification if feed cost is high
        if (dto.getCost() != null && dto.getCost() > 500) {
            notificationClient.sendNotification("High feeding cost recorded: " + dto.getCost() + " for animal " + dto.getAnimalId());
        }

        return mapToDTO(log);
    }

    public List<FeedLogDTO> getLogsByAnimal(Long animalId, String token) {
        authClient.validateToken(token);
        return feedLogRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private FeedLogDTO mapToDTO(FeedLog log) {
        return FeedLogDTO.builder()
                .id(log.getId())
                .animalId(log.getAnimalId())
                .feedType(log.getFeedType())
                .quantityKg(log.getQuantityKg())
                .feedTime(log.getFeedTime())
                .cost(log.getCost())
                .recordedBy(log.getRecordedBy())
                .build();
    }
}