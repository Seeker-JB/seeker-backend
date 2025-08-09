package com.seeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.EducationQualification;

public interface EducationQualificationDao extends JpaRepository<EducationQualification, Long> {
	
	
	List<EducationQualification> findByUserId(Long userId);


}
