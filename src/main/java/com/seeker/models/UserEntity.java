package com.seeker.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.seeker.enums.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "register")
public class UserEntity extends BaseEntity implements UserDetails {

	@Column(name = "full_name", nullable = false, length = 100)
	@NotBlank(message = "Full name is required")
	@Size(min = 2, max = 100)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	@Email
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	@Column(name = "description", nullable = false, length = 1000)
	@NotBlank(message = "Description is required")
	@Size(max = 1000, message = "Description too long")
	private String description;

	@Column(name = "dob", nullable = true)
	private LocalDate dob;

	@Column(name = "city", nullable = false, length = 100)
	private String city;

	@Column(name = "state", nullable = false, length = 100)
	private String state;

	@Column(name = "country", nullable = false, length = 100)
	private String country;

	@Column(name = "gender", nullable = true, length = 20)
	private String gender;

	@Column(name = "linkedin_url", length = 300, nullable = true)
	private String linkedinUrl;

	@Column(name = "github_url", length = 300, nullable = true)
	private String githubUrl;

	@Column(name = "website", nullable = true, length = 255)
	private String website;

	@Column(name = "protfolio_type", nullable = true)
	private int portfolio;
	
	@Column(name = "profile_picture_url", length = 500, nullable = true)
	private String profilePictureUrl;

	// ---------- UserDetails methods ----------
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email; // email as username
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
