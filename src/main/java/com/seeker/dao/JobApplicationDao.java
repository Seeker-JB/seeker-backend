package com.seeker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.JobApplication;
import com.seeker.models.JobPost;

public interface JobApplicationDao extends JpaRepository<JobApplication, Long>  {

	
	
	
	  Optional<JobApplication> findById(Long id);
	  List<JobApplication> findByUserId(Long userId);
	  List<JobApplication> findByJobPostId(Long jobPostId);
}
