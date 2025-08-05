package com.seeker.services;

import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seeker.custom_exceptions.ResourceNotFoundException;
import com.seeker.dao.BusinessProfileDao;
import com.seeker.dao.UserDao;
import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;

import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {

	private BusinessProfileDao businessProfileDao;

	private UserDao userDao;

	private ModelMapper modelMapper;

	private CloudinaryImageService cloud;
	
	
    @Override
	public BusinessProfileResponseDTO addBusinessProfile(BusinessProfileRequestDTO dto) {

		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = null;
		
		if (principle instanceof UserEntity user) {
//			UserEntity user = (UserEntity) principle;
			email = user.getEmail();
		}
		
		UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

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
	public BusinessProfileResponseDTO getBusinessProfileByUser(UserEntity user) {
		
		
		BusinessProfile business = businessProfileDao.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return  modelMapper.map(business, BusinessProfileResponseDTO.class);
	}

	@Override
	public BusinessProfileResponseDTO updateBusinessProfile(BusinessProfileRequestDTO dto, UserEntity user) {
		

		

		BusinessProfile existingProfile = businessProfileDao.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Business profile not found for Current User "));

		    // Update fields from DTO to entity (manual or using ModelMapper)
		    modelMapper.map(dto, existingProfile);

		    // Save the updated profile
		    BusinessProfile updatedProfile = businessProfileDao.save(existingProfile);

		    // Return the response DTO
		    return modelMapper.map(updatedProfile, BusinessProfileResponseDTO.class);
	}

	@Override
	public void deleteBusinessProfileByUser(UserEntity user) {
		BusinessProfile profile = businessProfileDao.findByUser(user)
		        .orElseThrow(() -> new ResourceNotFoundException("Business profile not found"));

		businessProfileDao.delete(profile);
		
	}

}
