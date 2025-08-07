package com.seeker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.PostRequestDto;
import com.seeker.dtos.PostResponseDto;
import com.seeker.services.PostService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

	private PostService postService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createPost(@Valid @ModelAttribute PostRequestDto postDto){
		String response = postService.createPost(postDto);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<ApiResponse> updatePost(@PathVariable Long postId, @Valid @ModelAttribute PostRequestDto postDto){
		String response = postService.updatePost(postId, postDto);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
		PostResponseDto response = postService.getPost(postId);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/getAllPostByUser/{postId}")
	public ResponseEntity<?> getAllPostByUserId(@PathVariable Long userId){
		return ResponseEntity.ok().body(postService.getAllPostsWithLikeStatusForCurrentUser(userId));
	}
	
	@DeleteMapping("post/{postId}")
	public ResponseEntity<?> deleteJobPost(@PathVariable Long postId) {
		String response = postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse(true, response));
	}
}
