package com.seeker.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.LikeDao;
import com.seeker.dao.PostDao;
import com.seeker.dtos.PostRequestDto;
import com.seeker.dtos.PostResponseDto;
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
	private LikeDao likeDao;
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
		return "Poste'd Successfull";

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

		postDao.save(post); // no need of doing this this can me automaticly done by @Transactional
		return "Posted Successfull";

	}

	public PostResponseDto getPost(Long postId) {
		Post post = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find post"));
		
		return modelMapper.map(post, PostResponseDto.class);
	}

	public List<PostResponseDto> getAllPostsWithLikeStatusForCurrentUser(Long userId) {
		UserEntity currentUser = securityContextUserProvider.getCurrentUser();

		List<Post> posts = postDao.findByUser_Id(userId);

		List<PostResponseDto> responseList = new ArrayList<>();

		for (Post post : posts) {

			PostResponseDto dto = modelMapper.map(post, PostResponseDto.class);

			boolean liked = likeDao.existsByUserAndPost(currentUser, post);
			dto.setLikedByCurrentUser(liked);

			responseList.add(dto);
		}

		return responseList;
	}

}
