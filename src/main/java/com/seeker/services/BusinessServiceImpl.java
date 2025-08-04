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


@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

	
	
	@Autowired
	private BusinessProfileDao  businessProfileDao;
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CloudinaryImageService cloud;
	
	
	public BusinessProfileResponseDTO addBusinessProfile(BusinessProfileRequestDTO dto) {
		 String email = SecurityContextHolder.getContext().getAuthentication().getName();
		    UserEntity user = userDao.findByEmail(email)
		        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

		    BusinessProfile profile = modelMapper.map(dto, BusinessProfile.class);
		    
		    profile.setUser(user);
		    
		    String logourl = null;
		    
		    if(dto.getLogo() !=  null) {
		    logourl =  cloud.upload(dto.getLogo());
		    }
		    
		    profile.setLogoUrl(logourl);
		    
		   
		    
		    
		    
		    return modelMapper.map(businessProfileDao.save(profile), BusinessProfileResponseDTO.class);
		    
		    
		    
	}

	
	

}
