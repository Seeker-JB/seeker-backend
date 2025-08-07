package com.seeker.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.dao.LikeDao;
import com.seeker.dao.PostDao;
import com.seeker.models.Like;
import com.seeker.models.Post;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

	private LikeDao likeDao;
	private PostDao postDao;
	private AuthenticatedUserProvider securityUserProvider;

	public String doLikeDislike(Long postId) {

		UserEntity user = securityUserProvider.getCurrentUser();

		Post post = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

		Optional<Like> checkLike = likeDao.findByUserAndPost(user, post);

		if (checkLike.isPresent()) {
			likeDao.delete(checkLike.get());
			return "Dislike successfull";
		} else {
			
			Like like = new Like();
			like.setUser(user);
			like.setPost(post);
			likeDao.save(like);
			return "Like successfull";
		}
	}
}
