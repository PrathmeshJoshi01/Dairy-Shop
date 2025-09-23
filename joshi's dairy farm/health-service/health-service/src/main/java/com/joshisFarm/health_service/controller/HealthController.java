package com.joshisFarm.health_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.health_service.dto.HealthRecordDTO;
import com.joshisFarm.health_service.service.HealthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @PostMapping
    public ResponseEntity<HealthRecordDTO> addRecord(@RequestBody HealthRecordDTO dto,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(healthService.addRecord(dto, token));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<HealthRecordDTO>> getRecordsByAnimal(@PathVariable Long animalId,
                                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(healthService.getRecordsByAnimal(animalId, token));
    }
}