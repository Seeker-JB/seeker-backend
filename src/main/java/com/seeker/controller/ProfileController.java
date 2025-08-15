package com.seeker.controller;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.UserDao;
import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.BusinessProfileResponseDto;
import com.seeker.dtos.UserProfileResponseDto;
import com.seeker.enums.Role;
import com.seeker.models.UserEntity;
import com.seeker.services.ProfileService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserDao userDao;

    private final ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        UserEntity user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));


        if (user.getRole() == Role.USER) {
        	UserProfileResponseDto profile = profileService.getUserProfile(user);
            return ResponseEntity.ok(profile);
        } else if (user.getRole() == Role.BUSINESS) {
            BusinessProfileResponseDto profile = profileService.getBusinessProfile(user);
            return ResponseEntity.ok(profile);
        } else {
            throw new IllegalStateException("Unsupported role: " + user.getRole());
        }
    }
}
