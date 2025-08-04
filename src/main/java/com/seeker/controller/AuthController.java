package com.seeker.controller;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.CreationSuccessDto;
import com.seeker.dtos.JwtRequestDto;
import com.seeker.dtos.JwtResponseDto;
import com.seeker.models.UserEntity;
import com.seeker.security.JwtHelper;
import com.seeker.services.AuthServiceImp;

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

	@PostMapping("/create-user")
	private ResponseEntity<?> createUser(@RequestBody UserEntity user){
    	try {
    		CreationSuccessDto response = new CreationSuccessDto(true, authservice.createUser(user));
    		return ResponseEntity.status(HttpStatus.SC_CREATED).body(response);
    		
		} catch (IllegalArgumentException e) {
			CreationSuccessDto response = new CreationSuccessDto(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}catch(DataIntegrityViolationException e) {
			CreationSuccessDto response = new CreationSuccessDto(false, e.getMessage());
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
    	}
    }
}
