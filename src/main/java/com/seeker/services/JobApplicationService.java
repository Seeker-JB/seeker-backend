package com.seeker.services;

import java.util.List;

import com.seeker.dtos.JobApplicationRequestDTO;
import com.seeker.dtos.JobApplicationResponseDTO;

public interface JobApplicationService {
	
	JobApplicationResponseDTO getById(Long id);

	
	JobApplicationResponseDTO applyToJob(Long jobPostId, JobApplicationRequestDTO dto);
	
	List<JobApplicationResponseDTO> getApplicationsForJobPost(Long jobPostId);
}
