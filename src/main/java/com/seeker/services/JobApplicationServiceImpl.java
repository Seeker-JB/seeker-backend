package com.seeker.services;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.OwnExceptions.UnauthorizedException;
import com.seeker.dao.JobApplicationDao;
import com.seeker.dao.JobPostDao;
import com.seeker.dao.UserDao;
import com.seeker.dtos.JobApplicationRequestDTO;
import com.seeker.dtos.JobApplicationResponseDTO;
import com.seeker.models.JobApplication;
import com.seeker.models.JobPost;
import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationDao jobApplicationDao;
    private final JobPostDao jobPostDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final CloudinaryImageService cloud;
    private final AuthenticatedUserProvider aup;

    
   //Applicant User's Method
    // ✅ Apply to a job post
    @Override
    public JobApplicationResponseDTO applyToJob(Long jobPostId,  JobApplicationRequestDTO dto) {
    	UserEntity currentUser = aup.getCurrentUser();

    	JobPost jobPost = jobPostDao.findById(jobPostId)
    	        .orElseThrow(() -> new ResourceNotFoundException("Job post not found"));

    	// 1. Map DTO → Entity
    	JobApplication application = modelMapper.map(dto, JobApplication.class);

    	// 2. Handle resume upload
    	if (dto.getResume() != null && !dto.getResume().isEmpty()) {
    	    String uploadedUrl = cloud.upload(dto.getResume());
    	    application.setResumeUrl(uploadedUrl);
    	}

    	// 3. Set applicant user
    	application.setUser(currentUser);

    	// ✅ 4. Use helper method to manage the relationship properly
    	jobPost.addJobApplication(application);

    	// 5. Save through application DAO (or optionally jobPostDao)
    	JobApplication saved = jobApplicationDao.save(application);

    	// 6. Map and return DTO
    	return modelMapper.map(saved, JobApplicationResponseDTO.class);
    }

    // ✅ Get all applications submitted by current user
    
    
   

    
    //Business Side's Method 
    // ✅ Get all applications for a specific job post (for job owner)
    @Override
    public List<JobApplicationResponseDTO> getApplicationsForJobPost(Long jobPostId) {
        UserEntity currentUser = aup.getCurrentUser();

        JobPost jobPost = jobPostDao.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Job post not found"));

        // check if current user is the owner of the job post
        if (!jobPost.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("Unauthorized access to job applications");
        }

        List<JobApplication> applications = jobApplicationDao.findByJobPostId(jobPostId);

        return applications.stream()
                .map(app -> modelMapper.map(app, JobApplicationResponseDTO.class))
                .toList();
    }

    
    
    
    @Override
    public JobApplicationResponseDTO getById(Long id) {
        JobApplication application = jobApplicationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));


        return modelMapper.map(application, JobApplicationResponseDTO.class);
    }

   
    
    
}
