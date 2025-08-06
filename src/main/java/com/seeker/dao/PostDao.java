package com.seeker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seeker.models.Post;
import com.seeker.models.UserEntity;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {

	public Optional<Post> findByUser(UserEntity user);
}
