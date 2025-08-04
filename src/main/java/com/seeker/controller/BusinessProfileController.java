package com.seeker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dao.UserDao;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;
import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;
import com.seeker.services.BusinessService;
import com.seeker.services.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class BusinessProfileController {

	private BusinessService businessService;

	private CustomUserDetailsService userDetails;

	private UserDao userDao;

	private ModelMapper modelmapper;

	@PostMapping("/business-profile")
	public ResponseEntity<?> createBusinessProfile(@ModelAttribute BusinessProfileRequestDTO dto) {
		
		BusinessProfileResponseDTO response = businessService.addBusinessProfile(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// Get Business Profile for current user
//    @GetMapping
//    public ResponseEntity<BusinessProfileResponseDTO> getBusinessProfile(
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        BusinessProfileResponseDTO response = businessService.getBusinessProfileByUserId(userDetails.getId());
//        return ResponseEntity.ok(response);
//    }

}
