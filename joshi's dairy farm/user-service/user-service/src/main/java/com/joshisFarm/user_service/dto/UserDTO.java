package com.joshisFarm.user_service.dto;

import com.joshisFarm.user_service.entity.Role;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Long id;
	private String name;
	private String location;
	private Double earning;
	private Integer cowsCount;
	private Long companyId;
	private Role role;
}
