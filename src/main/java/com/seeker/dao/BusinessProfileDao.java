package com.seeker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.BusinessProfile;

public interface BusinessProfileDao extends JpaRepository<BusinessProfile, Long> {
	
	

}
