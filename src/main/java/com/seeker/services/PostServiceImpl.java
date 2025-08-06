package com.seeker.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.PostDao;
import com.seeker.dtos.PostRequestDto;
import com.seeker.models.Post;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private PostDao postDao;
	private AuthenticatedUserProvider securityContextUserProvider;
	private ModelMapper modelMapper;
	private CloudinaryImageServiceImpl cloudinary;

	public String createPost(PostRequestDto postDto) {

		UserEntity user = securityContextUserProvider.getCurrentUser();

		Post post = modelMapper.map(postDto, Post.class);

		post.setUser(user);

		MultipartFile file = postDto.getMediaImg();
		String url = null;
		if (file != null && !(file.isEmpty())) {
			url = cloudinary.upload(file);
		}

		post.setMediaUrl(url);

		postDao.save(post);
		return "Posted Successfull";

	}

	public String updatePost(PostRequestDto postDto) {

		UserEntity user = securityContextUserProvider.getCurrentUser();

		Post post = postDao.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Post not Found"));

		modelMapper.map(postDto, post);

		MultipartFile file = postDto.getMediaImg();
		String url = null;
		if (file != null && !(file.isEmpty())) {
			url = cloudinary.upload(file);
		}

		post.setMediaUrl(url);

		postDao.save(post);  // no need of doing this this can me automaticly done by @Transactional
		return "Posted Successfull";

	}
}
