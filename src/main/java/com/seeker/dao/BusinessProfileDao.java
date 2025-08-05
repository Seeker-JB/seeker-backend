package com.seeker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.BusinessProfile;
import com.seeker.models.UserEntity;

import java.util.List;
import java.util.Optional;


public interface BusinessProfileDao extends JpaRepository<BusinessProfile, Long> {
	
	
	Optional<BusinessProfile> findByUser(UserEntity user);
	

}
