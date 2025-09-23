package com.joshisFarm.feed_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.feed_service.dto.FeedLogDTO;
import com.joshisFarm.feed_service.service.FeedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<FeedLogDTO> logFeeding(@RequestBody FeedLogDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(feedService.logFeeding(dto, token));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<FeedLogDTO>> getLogsByAnimal(@PathVariable Long animalId,
                                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(feedService.getLogsByAnimal(animalId, token));
    }
}