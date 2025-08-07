package com.seeker.services;

import java.util.List;

import com.seeker.dtos.PostRequestDto;
import com.seeker.dtos.PostResponseDto;

public interface PostService {

	public String createPost(PostRequestDto postDto);

	public String updatePost(Long postId, PostRequestDto postDto);
	
	public PostResponseDto getPost(Long postId);

	public List<PostResponseDto> getAllPostsWithLikeStatusForCurrentUser(Long userId);
	
	public String deletePost(Long postId);
}
