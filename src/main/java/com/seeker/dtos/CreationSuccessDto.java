package com.seeker.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreationSuccessDto {

	private boolean success;
	private String message;
}
