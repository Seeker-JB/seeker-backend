package com.seeker.services;

import com.seeker.dtos.BusinessProfileRequestDTO;
import com.seeker.dtos.BusinessProfileResponseDTO;
import com.seeker.models.BusinessProfile;

public interface BusinessService {

	BusinessProfileResponseDTO addBusinessProfile(BusinessProfileRequestDTO dto);

}
