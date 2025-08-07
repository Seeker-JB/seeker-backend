package com.seeker.controller;

import org.springframework.http.ResponseEntity;
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
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updatePost(@Valid @ModelAttribute PostRequestDto postDto){
		String response = postService.updatePost(postDto);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
		PostResponseDto response = postService.getPost(postId);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/getAllPostByUser/{postId}")
	public ResponseEntity<?> getAllPostByUserId(@PathVariable  Long userId){
		return ResponseEntity.ok().body(postService.getAllPostsWithLikeStatusForCurrentUser(userId));
	}
}
