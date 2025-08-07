package com.seeker.controller;

import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.ProjectRequestDto;
import com.seeker.dtos.ProjectResponseDto;
import com.seeker.services.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // ✅ Create project (uses authenticated user)
    @PostMapping("/project")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectRequestDto requestDto) {
    	
    	ProjectResponseDto dto =  projectService.createProject(requestDto);
   
        return ResponseEntity.ok(new ApiResponse(true, "Project Created Successfuly"));
    }

    // ✅ Get all projects by userId (public or business use-case)
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getProjectsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(projectService.getProjectsByUserId(userId));
    }

    // ✅ Get project by project ID
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    // ✅ Update project (uses authenticated user)
    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectRequestDto requestDto) {
    	projectService.updateProject(projectId, requestDto);
        return ResponseEntity.ok(new ApiResponse(true, "Updated Project Successfully"));
    }

    // ✅ Delete project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok(new ApiResponse(true, "Deleted Project"));
    }
}
