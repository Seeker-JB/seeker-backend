package com.seeker.services;

import java.util.List;

import com.seeker.dtos.JobPostRequestDTO;
import com.seeker.dtos.JobPostResponseDTO;
import com.seeker.models.JobPost;

import jakarta.validation.Valid;

public interface JobPostService {

	String createJobPost(@Valid JobPostRequestDTO requestDTO);

//
//	List<JobPostResponseDTO> getJobPostsForCurrentUser();

	List<JobPostResponseDTO> getJobPostsByUserId(Long userId);

	JobPostResponseDTO getJobPostById(Long id);

	String updateJobPost(Long id, @Valid JobPostRequestDTO requestDTO);

	String deleteJobPost(Long id);
	
	
	
	

}
