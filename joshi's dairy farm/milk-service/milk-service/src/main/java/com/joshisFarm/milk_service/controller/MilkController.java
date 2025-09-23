package com.joshisFarm.milk_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.milk_service.dto.MilkDTO;
import com.joshisFarm.milk_service.service.MilkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/milk")
@RequiredArgsConstructor
public class MilkController {

    private final MilkService milkService;

    @PostMapping
    public ResponseEntity<MilkDTO> addMilkLog(@RequestBody MilkDTO dto,
                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(milkService.addMilkLog(dto, token));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<MilkDTO>> getLogsByAnimal(@PathVariable Long animalId,
                                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(milkService.getLogsByAnimal(animalId, token));
    }

    @GetMapping("/company/{companyId}/daily")
    public ResponseEntity<List<MilkDTO>> getDailyReport(@PathVariable Long companyId,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(milkService.getDailyReport(companyId, token));
    }
}