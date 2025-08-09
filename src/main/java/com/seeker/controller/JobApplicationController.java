package com.seeker.controller;

import com.seeker.dtos.JobApplicationRequestDTO;
import com.seeker.dtos.JobApplicationResponseDTO;
import com.seeker.services.JobApplicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeker/applications")
@RequiredArgsConstructor
public class JobApplicationController {

	private final JobApplicationService jobApplicationService;

	// ------------------------
	// 🎯 Applicant Endpoints
	// ------------------------

	// ✅ Apply to a job
	@PostMapping("/apply/{jobPostId}")
	public ResponseEntity<JobApplicationResponseDTO> applyToJob(@PathVariable Long jobPostId,
			@ModelAttribute JobApplicationRequestDTO dto
	) {
		JobApplicationResponseDTO response = jobApplicationService.applyToJob(jobPostId, dto);
		return ResponseEntity.ok(response);
	}

	// ------------------------
	// 🧑‍💼 Business Endpoints
	// ------------------------

	// ✅ View a single application by ID (optional use)
	@GetMapping("/{id}")
	public ResponseEntity<JobApplicationResponseDTO> getApplicationById(@PathVariable Long id) {
		JobApplicationResponseDTO response = jobApplicationService.getByApplicationId(id);
		return ResponseEntity.ok(response);
	}

	// ✅ Get all applications for a job post (owned by business)
	@GetMapping("/jobpost/{jobPostId}")
	public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsForJobPost(@PathVariable Long jobPostId) {
		List<JobApplicationResponseDTO> responses = jobApplicationService.getApplicationsForJobPost(jobPostId);
		return ResponseEntity.ok(responses);
	}

	// ✅ Delete an application (business only)
	@DeleteMapping("/{applicationId}")
	public ResponseEntity<Void> deleteApplicationByBusiness(@PathVariable Long applicationId) {
		jobApplicationService.deleteApplicationByBusiness(applicationId);
		return ResponseEntity.noContent().build();
	}
}