package com.seeker.services;

import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileUpdateDto;
import com.seeker.dtos.UserProfileRequestDto;
import com.seeker.dtos.UserProfileUpdateDto;
import com.seeker.models.UserEntity;

public interface AuthService {
	
	public void doauthenticate(String email, String password);
	
	public String addBusinessProfile(BusinessProfileRequestDTO dto);
	public String updateBusinessProfile(BusinessProfileUpdateDto dto);
	
	public String createUserProfile(UserProfileRequestDto userDto);
	public String updateUserProfile(UserProfileUpdateDto userDto);
	
	public void encodePassword(UserEntity user, String password);
	public String deleteBusinessProfileByUser();
	
}
