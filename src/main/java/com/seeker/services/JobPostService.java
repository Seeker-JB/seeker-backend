package com.seeker.services;

import java.util.List;

import com.seeker.dtos.JobPostRequestDTO;
import com.seeker.dtos.JobPostResponseDTO;
import com.seeker.models.JobPost;

import jakarta.validation.Valid;

public interface JobPostService {

	void createJobPost(@Valid JobPostRequestDTO requestDTO);


	List<JobPostResponseDTO> getJobPostsForCurrentUser();

	List<JobPostResponseDTO> getJobPostsByUserId(Long userId);

	JobPostResponseDTO getJobPostById(Long id);

	JobPostResponseDTO updateJobPost(Long id, @Valid JobPostRequestDTO requestDTO);

	void deleteJobPost(Long id);
	
	
	
	

}
