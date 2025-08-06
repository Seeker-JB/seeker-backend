package com.seeker.controller;

import com.seeker.dtos.JobApplicationRequestDTO;
import com.seeker.dtos.JobApplicationResponseDTO;
import com.seeker.services.JobApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-posts")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    // ✅ 1. Apply to a Job Post (Job Seeker)
    @PostMapping("/{jobPostId}/applications")
    public ResponseEntity<JobApplicationResponseDTO> applyForJob(
            @PathVariable Long jobPostId,
            @Valid @ModelAttribute JobApplicationRequestDTO dto) {
        JobApplicationResponseDTO response = jobApplicationService.applyToJob(jobPostId, dto);
        return ResponseEntity.ok(response);
    }

    // ✅ 2. Get all applications made by current authenticated user
    @GetMapping("/applications/my")
    public ResponseEntity<List<JobApplicationResponseDTO>> getMyApplications() {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByCurrentUser());
    }

    // ✅ 3. Get all applications for a specific job post (Business User)
    @GetMapping("/{jobPostId}/applications")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsForJobPost(
            @PathVariable Long jobPostId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsForJobPost(jobPostId));
    }
    
    
    
    
    
}
