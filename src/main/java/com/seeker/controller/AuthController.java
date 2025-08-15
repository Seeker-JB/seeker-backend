package com.seeker.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileUpdateDto;
import com.seeker.dtos.JwtRequestDto;
import com.seeker.dtos.JwtResponseDto;
import com.seeker.dtos.UserProfileRequestDto;
import com.seeker.dtos.UserProfileUpdateDto;
import com.seeker.models.UserEntity;
import com.seeker.security.JwtHelper;
import com.seeker.services.AuthServiceImp;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

	private UserDetailsService userDetailsService;

	private JwtHelper jwtHelper;

	private AuthServiceImp authservice;

	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto request) {
		
		authservice.doauthenticate(request.getEmail(), request.getPassword());

		UserEntity userDetails =(UserEntity)userDetailsService.loadUserByUsername(request.getEmail());
		String token = jwtHelper.generateToken(userDetails);

		JwtResponseDto response = JwtResponseDto.builder().jwtToken(token).email(userDetails.getUsername()).portfolioType(userDetails.getPortfolio()).role(userDetails.getRole().toString()).id(userDetails.getId()).success(true).build();

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/business-profile")
	public ResponseEntity<?> createBusinessProfile(@Valid @ModelAttribute BusinessProfileRequestDTO dto) {

		String response = authservice.addBusinessProfile(dto);

		return ResponseEntity.status(HttpStatus.SC_CREATED).body(new ApiResponse(true , response));
	}
	
	@GetMapping("/getSingleBusiness")
	public ResponseEntity<?> getSingleBusiness() {

		BusinessProfileUpdateDto response = authservice.getSingleBusiness(); 

		return ResponseEntity.ok(response);
	}

	@PutMapping("/business-profile")
	public ResponseEntity<?> updateBusinessProfile(@Valid @ModelAttribute BusinessProfileUpdateDto dto) {

		authservice.updateBusinessProfile(dto); // or user.getId() if needed

		return ResponseEntity.ok(new ApiResponse(true, "Business Profile was updated successfully"));
	}

	@PostMapping("/user-profile")
	public ResponseEntity<?> createUserProfile(@Valid @ModelAttribute UserProfileRequestDto user){
		return ResponseEntity.ok().body(new ApiResponse(true,authservice.createUserProfile(user)));
	}
	
	@GetMapping("/getSingleUser")
	public ResponseEntity<?> getSingleUser() {

		UserProfileUpdateDto response = authservice.getSingleUser(); 

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/user-profile")
	public ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute UserProfileUpdateDto dto) {

		String response = authservice.updateUserProfile(dto); 

		return ResponseEntity.ok(new ApiResponse(true, response));
	}
	
	@DeleteMapping("/delete-profile")
	public ResponseEntity<?> deleteProfile() {

		String response = authservice.deleteProfile();

		return ResponseEntity.ok(new ApiResponse(true, response));
	}
}
