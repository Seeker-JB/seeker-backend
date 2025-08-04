package com.seeker.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnSuccessfullDto {

	private boolean success;
	private String message;
}
