package com.seeker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.UserEntity;

public interface UserDao extends JpaRepository<UserEntity,Long>{
	
	public Optional<UserEntity> findByEmail(String email);
	
	

}
