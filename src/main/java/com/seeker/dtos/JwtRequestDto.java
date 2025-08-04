package com.seeker.dtos;

import lombok.*;

@Getter
@Setter
public class JwtRequestDto {

	private String email;
	
	private String password;
}
