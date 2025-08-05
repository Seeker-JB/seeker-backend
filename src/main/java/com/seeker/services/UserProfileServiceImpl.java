package com.seeker.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.UserProfileDao;
import com.seeker.dtos.UserProfileRequestDto;
import com.seeker.models.UserEntity;
import com.seeker.models.UserProfile;
import com.seeker.security.AuthenticatedUserProvider;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserProfileServiceImpl {

	private UserProfileDao userProfileDao;

	private ModelMapper modelMapper;

	private CloudinaryImageServiceImpl cloudinary;

	private AuthenticatedUserProvider securityContextUserProvider;

	public String createUserProfile(UserProfileRequestDto userDto) {
		UserProfile userProfile = modelMapper.map(userDto, UserProfile.class);
		UserEntity user = securityContextUserProvider.getCurrentUser();

		try {

			if (userDto.getProfilePicture() != null && !userDto.getProfilePicture().isEmpty()) {
				String uploadedUrl = cloudinary.upload(userDto.getProfilePicture());
				userProfile.setProfilePictureUrl(uploadedUrl);
			}
			UserProfile saved = userProfileDao.save(userProfile);
			return "Profile created";

		} catch (Exception ex) {

			throw new RuntimeException("Failed to create user profile: " + ex.getMessage(), ex);
		}
	}

	public String updateUserProfile(UserProfileRequestDto userDto) {
		UserEntity user = securityContextUserProvider.getCurrentUser();

		UserProfile existing = userProfileDao.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for user id"));

		try {
			
			modelMapper.map(userDto, existing);

			MultipartFile newPic = userDto.getProfilePicture();
			
			if (newPic != null && !newPic.isEmpty()) {
				
				String uploadedUrl = cloudinary.upload(newPic);
				existing.setProfilePictureUrl(uploadedUrl);
			}

			UserProfile saved = userProfileDao.save(existing);
			
			return "Profile updated with id";
			
		} catch (Exception ex) {
			throw new RuntimeException("Failed to update user profile: " + ex.getMessage(), ex);
		}
	}

}
