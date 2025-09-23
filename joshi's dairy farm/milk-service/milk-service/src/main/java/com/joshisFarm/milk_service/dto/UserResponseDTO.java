package com.joshisFarm.milk_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

	private Long id;
	private String userName;
	private String email;
	private String role;
}

