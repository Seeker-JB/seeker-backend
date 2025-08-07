package com.seeker.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
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

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = jwtHelper.generateToken(userDetails);

		JwtResponseDto response = JwtResponseDto.builder().jwtToken(token).email(userDetails.getUsername()).success(true).build();

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/business-profile")
	public ResponseEntity<?> createBusinessProfile(@Valid @ModelAttribute BusinessProfileRequestDTO dto) {

		String response = authservice.addBusinessProfile(dto);

		return ResponseEntity.status(HttpStatus.SC_CREATED).body(new ApiResponse(true , response));
	}

	@PutMapping("/business-profile/update")
	public ResponseEntity<?> updateBusinessProfile(@Valid @ModelAttribute BusinessProfileUpdateDto dto) {

		authservice.updateBusinessProfile(dto); // or user.getId() if needed

		return ResponseEntity.ok(new ApiResponse(true, "Business Profile was updated successfully"));
	}

	@PostMapping("/user-profile")
	public ResponseEntity<?> createUserProfile(@Valid @ModelAttribute UserProfileRequestDto user){
		return ResponseEntity.ok().body(new ApiResponse(true,authservice.createUserProfile(user)));
	}
	
	@PutMapping("/user-profile")
	public ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute UserProfileUpdateDto dto) {

		String response = authservice.updateUserProfile(dto); 

		return ResponseEntity.ok(new ApiResponse(true, response));
	}
	
	@DeleteMapping("/business-profile")
	public ResponseEntity<?> deleteBusinessProfile() {

		String response = authservice.deleteBusinessProfileByUser();

		return ResponseEntity.ok(new ApiResponse(true, response));
	}
}
