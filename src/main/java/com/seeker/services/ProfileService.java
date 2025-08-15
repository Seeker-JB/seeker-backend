package com.seeker.services;

import com.seeker.dtos.BusinessProfileResponseDto;
import com.seeker.dtos.UserProfileResponseDto;
import com.seeker.models.UserEntity;

public interface ProfileService {

	UserProfileResponseDto getUserProfile(UserEntity user);

	BusinessProfileResponseDto getBusinessProfile(UserEntity user);

}
