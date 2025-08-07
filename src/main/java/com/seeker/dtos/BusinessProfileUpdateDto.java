package com.seeker.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProfileUpdateDto {

	@NotBlank(message = "Contact email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Business name is required")
	private String name;

	@NotBlank(message = "Description is required")
	@Size(max = 1000, message = "Description too long")
	private String description;

	@NotBlank(message = "Website is required")
	private String website;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Country is required")
	private String country;

	private MultipartFile profilePicture;

}
