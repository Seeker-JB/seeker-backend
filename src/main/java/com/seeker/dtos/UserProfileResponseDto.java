package com.seeker.dtos;

import java.time.LocalDate;
import java.util.List;

import com.seeker.enums.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

	private Long id;
    private String name;
    private String email;
    private Role role;

    private String profilePictureUrl;
    private String description;
    private LocalDate dob;
    private String city;
    private String state;
    private String country;
    private String gender;
    private String linkedinUrl;
    private String githubUrl;
    private Integer portfolio;

    private List<ProjectResponseDto> projects;
    private List<EducationQualificationResponseDto> education;
}
