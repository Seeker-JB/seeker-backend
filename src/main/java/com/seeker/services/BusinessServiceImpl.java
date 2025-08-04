package com.seeker.services;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
