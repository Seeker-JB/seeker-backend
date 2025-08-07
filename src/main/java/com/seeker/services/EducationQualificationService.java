package com.seeker.services;

import com.seeker.dtos.EducationQualificationRequestDto;
import com.seeker.dtos.EducationQualificationResponseDto;

import java.util.List;

public interface EducationQualificationService {

    String create(EducationQualificationRequestDto dto);

    

    String update(Long id, EducationQualificationRequestDto dto);

    String delete(Long id);

	List<EducationQualificationResponseDto> getAllEducation(Long userid);
}
