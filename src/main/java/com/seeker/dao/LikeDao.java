package com.seeker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seeker.models.Like;
import com.seeker.models.Post;
import com.seeker.models.UserEntity;

@Repository
public interface LikeDao extends JpaRepository<Like, Long> {

	boolean existsByUserAndPost(UserEntity user, Post post);
	
	Optional<Like> findByUserAndPost(UserEntity user, Post post);

}
