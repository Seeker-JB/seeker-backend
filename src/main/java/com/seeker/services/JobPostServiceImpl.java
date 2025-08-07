package com.seeker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.OwnExceptions.UnauthorizedException;

import com.seeker.dao.JobPostDao;
import com.seeker.dao.UserDao;
import com.seeker.dtos.JobPostRequestDTO;
import com.seeker.dtos.JobPostResponseDTO;
import com.seeker.models.JobPost;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor

public class JobPostServiceImpl implements JobPostService {

	private JobPostDao jobPostDao;

	private ModelMapper modelMapper;

	private AuthenticatedUserProvider aup;

	private UserDao userDao;

	@Override

	public String createJobPost(@Valid JobPostRequestDTO requestDTO) {

		UserEntity user = aup.getCurrentUser();

		JobPost jobpost = modelMapper.map(requestDTO, JobPost.class);

		jobpost.setUser(user);

		jobPostDao.save(jobpost);
		
		return "Posted Successfull";

	}

//	@Override
//	public List<JobPostResponseDTO> getJobPostsForCurrentUser() {
//		UserEntity user = aup.getCurrentUser();
//
//		List<JobPost> jobPosts = jobPostDao.findByUser(user);
//
//		List<JobPostResponseDTO> dtos = jobPosts.stream().map(jb -> {
//			// Map base fields
//			JobPostResponseDTO dto = modelMapper.map(jb, JobPostResponseDTO.class);
//
//			dto.setUserId(user.getId());
//
//			return dto;
//		}).collect(Collectors.toList());
//		return dtos;
//	}

	@Override
	public List<JobPostResponseDTO> getJobPostsByUserId(Long userId) {
		UserEntity user = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		List<JobPost> jobPosts = jobPostDao.findByUser(user);

		return jobPosts.stream().map(jb -> {
			JobPostResponseDTO dto = modelMapper.map(jb, JobPostResponseDTO.class);
			dto.setUserId(user.getId());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public JobPostResponseDTO getJobPostById(Long id) {
		JobPost jb = jobPostDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + id));

		JobPostResponseDTO dto = modelMapper.map(jb, JobPostResponseDTO.class);

		dto.setUserId(jb.getUser().getId());

		return dto;

	}

	@Override
	public String updateJobPost(Long id, @Valid JobPostRequestDTO requestDTO) {
		
		JobPost existing = jobPostDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + id));

		UserEntity currentUser = aup.getCurrentUser();
		if (!existing.getUser().getId().equals(currentUser.getId())) {
			throw new UnauthorizedException("You are not allowed to update this job post");
		}

		modelMapper.map(requestDTO, existing);

		JobPost jb = jobPostDao.save(existing);

		return "Update Successfull";
	}

	@Override
	public String deleteJobPost(Long id) {
		JobPost existing = jobPostDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + id));

		UserEntity currentUser = aup.getCurrentUser();
		if (!existing.getUser().getId().equals(currentUser.getId())) {
			throw new UnauthorizedException("You are not allowed to delete this job post");
		}

		jobPostDao.delete(existing);
		
		return "Delete Successfull";
	}

}
