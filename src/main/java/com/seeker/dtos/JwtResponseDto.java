package com.seeker.dtos;

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
	
	private String jwtToken;
	
	private String email;
}
