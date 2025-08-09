package com.seeker.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateDto {

	@NotBlank(message = "Contact email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Full name is required")
	@Size(min = 2, max = 100)
	private String name;

	@NotBlank(message = "Gender is required")
	private String gender;

	@Past(message = "Date of birth must be in the past")
	private LocalDate dob;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Country is required")
	private String country;

	@URL(message = "Invalid LinkedIn URL")
	private String linkedinUrl;

	@URL(message = "Invalid GitHub URL")
	private String githubUrl;

	@NotBlank(message = "Password is Required")
	private String password;

	@NotBlank(message = "Role is Required")
	@Enumerated(EnumType.STRING)
	private Role role;

	@NotBlank(message = "Select portfolio")
	private int portfolio;
	
	private MultipartFile profilePicture;
}
