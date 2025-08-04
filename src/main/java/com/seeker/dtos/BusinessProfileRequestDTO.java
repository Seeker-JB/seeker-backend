package com.seeker.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class BusinessProfileRequestDTO {

	@NotBlank(message = "Business name is required")
	private String businessName;

	@NotBlank(message = "Description is required")
	@Size(max = 1000, message = "Description too long")
	private String description;

	@NotBlank(message = "Contact email is required")
	@Email(message = "Invalid email format")
	private String contactEmail;

	@NotBlank(message = "Website is required")
	private String website;

	@NotBlank(message = "Address is required")
	private String address;

	private MultipartFile logo;

}
