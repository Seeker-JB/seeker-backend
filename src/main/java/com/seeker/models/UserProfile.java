package com.seeker.models;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class UserProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "full_name", nullable = false, length = 100)
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100)
    private String fullName;

    @Column(name = "gender", nullable = false, length = 20)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(name = "phone", nullable = false, length = 10, unique = true)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @Column(name = "dob")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Column(name = "city", nullable = false, length = 100)
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "state", nullable = false, length = 100)
    @NotBlank(message = "State is required")
    private String state;

    @Column(name = "country", nullable = false, length = 100)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "linkedin_url", length = 300)
    @URL(message = "Invalid LinkedIn URL")
    private String linkedinUrl;

    @Column(name = "github_url", length = 300)
    @URL(message = "Invalid GitHub URL")
    private String githubUrl;

    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureUrl;
}
