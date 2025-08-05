package com.seeker.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.CreationSuccessDto;
import com.seeker.dtos.UserProfileRequestDto;
import com.seeker.services.UserProfileServiceImpl;

@RestController
@RequestMapping("/seeker")
public class UserProfileController {

	UserProfileServiceImpl userProfileService;
	@PostMapping("/user-profile")
	public ResponseEntity<?> createUserProfile(@ModelAttribute UserProfileRequestDto user){
		return ResponseEntity.ok().body(new CreationSuccessDto(true,userProfileService.createUserProfile(user)));
	}
}
