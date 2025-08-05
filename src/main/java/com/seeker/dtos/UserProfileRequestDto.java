package com.seeker.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileRequestDto {

	    @NotBlank(message = "Full name is required")
	    @Size(min = 2, max = 100)
	    private String fullName;

	    @NotBlank(message = "Gender is required")
	    private String gender;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    private String phone;

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

	    private MultipartFile profilePicture;
}
