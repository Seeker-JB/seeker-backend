package com.seeker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.PostRequestDto;
import com.seeker.services.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

	private PostService postService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createPost(PostRequestDto postDto){
		String response = postService.createPost(postDto);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updatePost(PostRequestDto postDto){
		String response = postService.updatePost(postDto);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
	
//	@GetMapping("/getByUser")
//	public ResponseEntity<?>
}
