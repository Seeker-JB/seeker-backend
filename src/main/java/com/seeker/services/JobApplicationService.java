package com.seeker.services;

import java.util.List;

import com.seeker.dtos.JobApplicationRequestDTO;
import com.seeker.dtos.JobApplicationResponseDTO;

public interface JobApplicationService {

JobApplicationResponseDTO getByApplicationId(Long id);



void deleteApplicationByBusiness(Long applicationId);


JobApplicationResponseDTO applyToJob(Long jobPostId, JobApplicationRequestDTO dto);

List<JobApplicationResponseDTO> getApplicationsForJobPost(Long jobPostId);



}