package com.seeker.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

	@NotBlank(message = "Content cannot be blank")
	private String content;

	private MultipartFile mediaImg; // optional: image/video link
}
