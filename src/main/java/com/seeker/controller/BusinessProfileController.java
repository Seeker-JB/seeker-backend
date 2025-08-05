package com.seeker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dao.UserDao;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;
import com.seeker.dtos.CreationSuccessDto;
import com.seeker.dtos.UnSuccessfullDto;
import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;
import com.seeker.services.BusinessService;
import com.seeker.services.CustomUserDetailsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/seeker")
public class BusinessProfileController {

	private BusinessService businessService;

	private CustomUserDetailsService userDetails;

	private UserDao userDao;

	private ModelMapper modelmapper;

	@PostMapping("/business-profile")
	public ResponseEntity<?> createBusinessProfile(@Valid @ModelAttribute BusinessProfileRequestDTO dto) {
		
		BusinessProfileResponseDTO response = businessService.addBusinessProfile(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	
	
	
	@GetMapping("/business-profile")
	public ResponseEntity<?> getBusinessProfileForCurrentUser() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		if (!(principle instanceof UserEntity user)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UnSuccessfullDto(false, "Could'nt fetch user"));
	
		}
		
		

	    BusinessProfileResponseDTO dto = businessService.getBusinessProfileByUser(user);
	    return ResponseEntity.ok(dto);
	}
	
	
	
	@PutMapping("/business-profile")
	public ResponseEntity<?> updateBusinessProfile( @Valid
			@ModelAttribute BusinessProfileRequestDTO dto) {

	    // Get the authenticated user from SecurityContext
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    
	    if (!(principal instanceof UserEntity user)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body(new UnSuccessfullDto(false, "Could'nt fetch user"));
	    }

	    // Pass UserEntity to service layer for update (if needed)
	    businessService.updateBusinessProfile(dto, user); // or user.getId() if needed

	    return ResponseEntity.ok(new CreationSuccessDto(true, "Business Profile was updated successfully"));
	}
	
	
	
	@DeleteMapping("/business-profile")
	public ResponseEntity<?> deleteBusinessProfile() {
	    // Get the current authenticated user from SecurityContext
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (!(principal instanceof UserEntity user)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new UnSuccessfullDto(false, "Could'nt fetch user"));
	    }

	    // Delete business profile for this user
	    businessService.deleteBusinessProfileByUser(user);

	    return ResponseEntity.ok(new CreationSuccessDto(true, "Business Profile deleted successfully"));
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
