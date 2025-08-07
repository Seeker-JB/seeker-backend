package com.seeker.services;

import java.util.List;

import com.seeker.dtos.ProjectRequestDto;
import com.seeker.dtos.ProjectResponseDto;

import jakarta.validation.Valid;

public interface ProjectService {

	ProjectResponseDto createProject(@Valid ProjectRequestDto requestDto);

	List<ProjectResponseDto> getProjectsByUserId(Long userId);

	ProjectResponseDto getProjectById(Long projectId);

	ProjectResponseDto updateProject(Long projectId, @Valid ProjectRequestDto requestDto);

	void deleteProject(Long projectId);

}
