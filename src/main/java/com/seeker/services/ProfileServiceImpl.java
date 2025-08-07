package com.seeker.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.seeker.dtos.UserProfileResponseDto;
import com.seeker.models.UserEntity;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Transactional
@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private ProjectService projectService;
	
	private EducationQualificationService educationQualificationService;
	
	
	private ModelMapper modelMapper;
	
	@Override
	public UserProfileResponseDto getUserProfile(UserEntity user) {
	
		UserProfileResponseDto mappedUser = modelMapper.map(user, UserProfileResponseDto.class);
		
		mappedUser.setEducation(educationQualificationService.getAllEducation(user.getId()));
		
		mappedUser.setProjects(projectService.getProjectsByUserId(user.getId()));
		
		
		return mappedUser;
	}

	@Override
	public UserProfileResponseDto getBusinessProfile(UserEntity user) {
		
		return modelMapper.map(user, UserProfileResponseDto.class);
	}

}
