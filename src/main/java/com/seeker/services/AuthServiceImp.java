package com.seeker.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.dao.UserDao;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileUpdateDto;
import com.seeker.dtos.UserProfileRequestDto;
import com.seeker.dtos.UserProfileUpdateDto;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

	private AuthenticationManager authenticationManager;

	private UserDao userDao;

	private PasswordEncoder passwordEncoder;

	private ModelMapper modelMapper;

	private CloudinaryImageService cloudinary;

	private AuthenticatedUserProvider securityContextUserProvider;

	public void doauthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

		try {
			authenticationManager.authenticate(authToken);
		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException("Invalid email or password");
		}
	}

	@Override
	public String addBusinessProfile(BusinessProfileRequestDTO dto) {

		UserEntity profile = modelMapper.map(dto, UserEntity.class);

		encodePassword(profile, dto.getPassword());

		String profilePictureurl = null;

		if (dto.getProfilePicture() != null && !(dto.getProfilePicture().isEmpty())) {
			profilePictureurl = cloudinary.upload(dto.getProfilePicture());
		}

		profile.setProfilePictureUrl(profilePictureurl);

		userDao.save(profile);

		return "Created Successfull";

	}

	@Override
	public String updateBusinessProfile(BusinessProfileUpdateDto dto) {

		UserEntity existingProfile = securityContextUserProvider.getCurrentUser();

		// Update fields from DTO to entity (manual or using ModelMapper)
		modelMapper.map(dto, existingProfile);

		MultipartFile newPic = dto.getProfilePicture();

		if (dto.getProfilePicture() != null && !(dto.getProfilePicture().isEmpty())) {
			String profilePictureurl = cloudinary.upload(dto.getProfilePicture());
			existingProfile.setProfilePictureUrl(profilePictureurl);
		}
		
		userDao.save(existingProfile);

		return "Update Successfull";
	}

	public String createUserProfile(UserProfileRequestDto userDto) {

		UserEntity userProfile = modelMapper.map(userDto, UserEntity.class);
		encodePassword(userProfile, userDto.getPassword());

		if (userDto.getProfilePicture() != null && !userDto.getProfilePicture().isEmpty()) {
			String uploadedUrl = cloudinary.upload(userDto.getProfilePicture());
			userProfile.setProfilePictureUrl(uploadedUrl);
		}
		UserEntity saved = userDao.save(userProfile);
		return "Profile created";

	}

	public String updateUserProfile(UserProfileUpdateDto userDto) {
		UserEntity existingUser = securityContextUserProvider.getCurrentUser();

		modelMapper.map(userDto, existingUser);

		MultipartFile newPic = userDto.getProfilePicture();

		if (newPic != null && !newPic.isEmpty()) {

			String uploadedUrl = cloudinary.upload(newPic);
			existingUser.setProfilePictureUrl(uploadedUrl);
		}

		UserEntity saved = userDao.save(existingUser);

		return "Profile updated with id";

	}

	@Override
	public String deleteProfile() {

		UserEntity user = securityContextUserProvider.getCurrentUser();

		userDao.delete(user);

		return "Portfolio Deleted Successfull";

	}

	public void encodePassword(UserEntity user, String password) {

		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);

	}
}
