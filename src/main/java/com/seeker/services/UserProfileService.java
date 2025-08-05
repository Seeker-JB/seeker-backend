package com.seeker.services;

import org.springframework.stereotype.Service;

import com.seeker.dtos.UserProfileRequestDto;

import jakarta.transaction.Transactional;

public interface UserProfileService {

	public String createUserProfile(UserProfileRequestDto user);
}
