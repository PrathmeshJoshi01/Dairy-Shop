package com.joshisFarm.user_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.user_service.dto.AttendanceDTO;
import com.joshisFarm.user_service.dto.UserDTO;
import com.joshisFarm.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto,
                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.createUser(dto, token));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserDTO>> getUsersByCompany(@PathVariable Long companyId,
                                                           @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getUsersByCompany(companyId, token));
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable Long id,
                                                 @RequestBody UserDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateProfile(id, dto, token));
    }

    @PostMapping("/{id}/attendance")
    public ResponseEntity<AttendanceDTO> markAttendance(@PathVariable Long id,
                                                        @RequestParam boolean present,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.markAttendance(id, present, token));
    }

}
