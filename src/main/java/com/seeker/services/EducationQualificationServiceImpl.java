package com.seeker.services;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.OwnExceptions.UnauthorizedException;

import com.seeker.dao.EducationQualificationDao;
import com.seeker.dao.UserDao;
import com.seeker.dtos.EducationQualificationRequestDto;
import com.seeker.dtos.EducationQualificationResponseDto;
import com.seeker.models.EducationQualification;

import com.seeker.models.UserEntity;
import com.seeker.security.AuthenticatedUserProvider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationQualificationServiceImpl implements EducationQualificationService {

    @Autowired
    private EducationQualificationDao educationQualificationDao;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String create(EducationQualificationRequestDto dto) {
        UserEntity currentUser = authenticatedUserService.getCurrentUser();

        EducationQualification qualification = modelMapper.map(dto, EducationQualification.class);
        qualification.setUser(currentUser);

        educationQualificationDao.save(qualification);
        return "Education qualification added successfully.";
    }

    @Override
    public List<EducationQualificationResponseDto> getAllEducation(Long userid) {
    	
        List<EducationQualification> qualifications = educationQualificationDao.findByUserId(userid);

        return qualifications.stream()
                .map(q -> modelMapper.map(q, EducationQualificationResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String update(Long id, EducationQualificationRequestDto dto) {
    	UserEntity currentUser = authenticatedUserService.getCurrentUser();

        EducationQualification existing = educationQualificationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with id: " + id));

        if (!existing.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You are not allowed to update this record.");
        }

        modelMapper.map(dto, existing); // update fields
        educationQualificationDao.save(existing);

        return "Education qualification updated successfully.";
    }

    @Override
    public String delete(Long id) {
    	UserEntity currentUser = authenticatedUserService.getCurrentUser();

        EducationQualification qualification = educationQualificationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with id: " + id));

        if (!qualification.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You are not allowed to delete this record.");
        }

        educationQualificationDao.delete(qualification);
        
        return "Education Qualification deleted successfully";
    }
}
