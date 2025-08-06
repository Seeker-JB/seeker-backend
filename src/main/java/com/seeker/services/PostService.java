package com.seeker.services;

import com.seeker.dtos.PostRequestDto;

public interface PostService {

	public String createPost(PostRequestDto postDto);
	public String updatePost(PostRequestDto postDto);
}
