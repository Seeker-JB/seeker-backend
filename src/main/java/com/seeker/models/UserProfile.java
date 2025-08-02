package com.seeker.models;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
 public class UserProfile extends BaseEntity {
    

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

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
    
    private String profilePictureUrl;
}
