package com.seeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.JobPost;
import com.seeker.models.UserEntity;


public interface JobPostDao extends JpaRepository<JobPost, Long> {
	
	List<JobPost> findByUser(UserEntity user);

}
