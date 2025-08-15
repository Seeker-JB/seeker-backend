package com.seeker.dtos;

import com.seeker.enums.Role;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class JwtResponseDto {
 
	private boolean success;
	
	private Long id;
	
	private String jwtToken;
	
	private String email;
	
	private String role;
	
	private int portfolioType;
}
