package com.seeker.controller;

import com.seeker.dtos.ApiResponse;

import com.seeker.dtos.JobPostRequestDTO;
import com.seeker.dtos.JobPostResponseDTO;
import com.seeker.services.JobPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.seeker.models.*;

@RestController
@RequestMapping("/seeker")
@AllArgsConstructor
public class JobPostController {

	private JobPostService jobPostService;

	@PostMapping("/jobposts")
	public ResponseEntity<?> createJobPost(@Valid @RequestBody JobPostRequestDTO requestDTO) {
		String response = jobPostService.createJobPost(requestDTO);
		return ResponseEntity.ok(new ApiResponse(true, response));
	}

//    // Get all JobPosts			
//    @GetMapping("/jobposts/me")
//    public ResponseEntity<List<JobPostResponseDTO>> getMyJobPosts() {
//        List<JobPostResponseDTO> posts = jobPostService.getJobPostsForCurrentUser();
//        return ResponseEntity.ok(posts);
//    }
//
//    

	@GetMapping("/jobposts/user/{userId}")
	public ResponseEntity<List<JobPostResponseDTO>> getJobPostsByUserId(@PathVariable Long userId) {
		List<JobPostResponseDTO> posts = jobPostService.getJobPostsByUserId(userId);
		return ResponseEntity.ok(posts);
	}

	// Get single JobPost by ID
	@GetMapping("/jobpost/{id}")
	public ResponseEntity<?> getJobPostById(@PathVariable Long id) {
		JobPostResponseDTO post = jobPostService.getJobPostById(id);
		return ResponseEntity.ok(post);
	}

	@PutMapping("/jobposts/{id}")
	public ResponseEntity<?> updateJobPost(@PathVariable Long id, @Valid @RequestBody JobPostRequestDTO requestDTO) {
		String response = jobPostService.updateJobPost(id, requestDTO);
		return ResponseEntity.ok(new ApiResponse(true, response));
	}

	@DeleteMapping("jobposts/{id}")
	public ResponseEntity<?> deleteJobPost(@PathVariable Long id) {
		String response = jobPostService.deleteJobPost(id);
		return ResponseEntity.ok(new ApiResponse(true, response));
	}

}
