package com.seeker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.dtos.ApiResponse;
import com.seeker.services.LikeServiceImpl;

@RestController
@RequestMapping("/like")
public class LikeController {

	private LikeServiceImpl likeService;
	
	@PostMapping("/like-dislike")
	public ResponseEntity<?> likeDislike(@PathVariable Long postId){
		String response = likeService.doLikeDislike(postId);
		return ResponseEntity.ok().body(new ApiResponse(true,response));
	}
}
