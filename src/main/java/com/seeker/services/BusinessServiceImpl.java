package com.seeker.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seeker.OwnExceptions.AlreadyExistsException;
import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.BusinessProfileDao;
import com.seeker.dao.UserDao;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;
import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {

	private BusinessProfileDao businessProfileDao;

	private UserDao userDao;

	private ModelMapper modelMapper;

	private CloudinaryImageService cloud;
	
	
	private AuthenticatedUserProvider auth;
	
	
    @Override
	public BusinessProfileResponseDTO addBusinessProfile(BusinessProfileRequestDTO dto) {

		
		
		UserEntity user = auth.getCurrentUser();
		if (businessProfileDao.existsByUser(user)) {
		    throw new AlreadyExistsException("Business profile already exists for this user.");
		}
		BusinessProfile profile = modelMapper.map(dto, BusinessProfile.class);

		profile.setUser(user);

		String logourl = null;

		if (dto.getLogo() != null) {
			logourl = cloud.upload(dto.getLogo());
		}

		profile.setLogoUrl(logourl);

		return modelMapper.map(businessProfileDao.save(profile), BusinessProfileResponseDTO.class);

	}

	@Override
	public BusinessProfileResponseDTO getBusinessProfileByUser() {
		
		UserEntity user = auth.getCurrentUser();
		BusinessProfile business = businessProfileDao.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return  modelMapper.map(business, BusinessProfileResponseDTO.class);
	}

	@Override
	public BusinessProfileResponseDTO updateBusinessProfile(BusinessProfileRequestDTO dto) {
		

			UserEntity user = auth.getCurrentUser();

		   BusinessProfile existingProfile = businessProfileDao.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Business profile not found for Current User "));

		    // Update fields from DTO to entity (manual or using ModelMapper)
		    modelMapper.map(dto, existingProfile);

		    // Save the updated profile
		    BusinessProfile updatedProfile = businessProfileDao.save(existingProfile);

		    // Return the response DTO
		    return modelMapper.map(updatedProfile, BusinessProfileResponseDTO.class);
	}

	@Override
	public void deleteBusinessProfileByUser() {
		
		UserEntity user = auth.getCurrentUser();
		BusinessProfile profile = businessProfileDao.findByUser(user)
		        .orElseThrow(() -> new ResourceNotFoundException("Business profile not found"));

		businessProfileDao.delete(profile);
		
	}

}
