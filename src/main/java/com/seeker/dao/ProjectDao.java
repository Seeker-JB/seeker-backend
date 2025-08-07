package com.seeker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeker.models.Project;
import com.seeker.models.UserEntity;

public interface ProjectDao extends JpaRepository<Project, Long> {

	List<Project> findByUser(UserEntity user);

}
