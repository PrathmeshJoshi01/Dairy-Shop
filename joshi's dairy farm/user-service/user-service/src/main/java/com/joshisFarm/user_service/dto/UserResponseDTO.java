package com.joshisFarm.user_service.dto;

import com.joshisFarm.user_service.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
	private Long id;
	private String userName;
	private String email;
	private Role role;
}
