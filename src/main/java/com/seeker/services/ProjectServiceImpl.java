package com.seeker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeker.OwnExceptions.ResourceNotFoundException;

import com.seeker.dao.UserDao;
import com.seeker.dtos.ProjectRequestDto;
import com.seeker.dtos.ProjectResponseDto;
import com.seeker.models.Project;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;
import com.seeker.dao.ProjectDao;

import jakarta.validation.Valid;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectResponseDto createProject(@Valid ProjectRequestDto requestDto) {
        UserEntity currentUser = authenticatedUserProvider.getCurrentUser();

        Project project = modelMapper.map(requestDto, Project.class);
        project.setUser(currentUser);

        Project saved = projectDao.save(project);

        return modelMapper.map(saved, ProjectResponseDto.class);
    }

    @Override
    public List<ProjectResponseDto> getProjectsByUserId(Long userId) {
    	
		UserEntity user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));
    	
    	
        List<Project> projects = projectDao.findByUser(user);
        return projects.stream() 
                .map(project -> {
                    ProjectResponseDto dto = modelMapper.map(project, ProjectResponseDto.class);
                    dto.setUserId(userId);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto getProjectById(Long projectId) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        return modelMapper.map(project, ProjectResponseDto.class);
    }

    @Override
    public ProjectResponseDto updateProject(Long projectId, @Valid ProjectRequestDto requestDto) {
        UserEntity currentUser = authenticatedUserProvider.getCurrentUser();

        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!project.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized to update this project");
        }

        // Map new values from DTO to existing entity (overwrites matching fields only)
        modelMapper.map(requestDto, project);

        Project updated = projectDao.save(project);
        return modelMapper.map(updated, ProjectResponseDto.class);
    }


    @Override
    public void deleteProject(Long projectId) {
        UserEntity currentUser = authenticatedUserProvider.getCurrentUser();

        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!project.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized to delete this project");
        }

        projectDao.delete(project);
    }
}
