package com.joshisFarm.auth_service.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joshisFarm.auth_service.dto.AuthResponse;
import com.joshisFarm.auth_service.dto.LoginRequest;
import com.joshisFarm.auth_service.dto.RegisterRequest;
import com.joshisFarm.auth_service.dto.UserResponseDTO;
import com.joshisFarm.auth_service.entity.Role;
import com.joshisFarm.auth_service.entity.User;
import com.joshisFarm.auth_service.repository.UserRepository;
import com.joshisFarm.auth_service.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .companyId(request.getCompanyId())
                .build();
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }
    
    public UserResponseDTO validateToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Parse token and get user details
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .role(role)
                .build();
    }
}