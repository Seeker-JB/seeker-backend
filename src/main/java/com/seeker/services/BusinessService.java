package com.seeker.services;

import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;
import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;

public interface BusinessService {

	public BusinessProfileResponseDTO addBusinessProfile(BusinessProfileRequestDTO dto);

	public BusinessProfileResponseDTO getBusinessProfileByUser();

	public BusinessProfileResponseDTO updateBusinessProfile(BusinessProfileRequestDTO dto);

	public void deleteBusinessProfileByUser();
	

	
}
