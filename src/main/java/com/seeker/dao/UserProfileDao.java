package com.seeker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seeker.models.UserEntity;
import com.seeker.models.UserProfile;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Long> {

	public Optional<UserProfile> findByUser(UserEntity user);
}
