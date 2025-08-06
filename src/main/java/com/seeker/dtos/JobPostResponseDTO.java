package com.seeker.dtos;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class JobPostResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String companyName;
    private String requiredSkills;
    private String experience;
    private String location;
    private String technology;

    // Optional: Basic info about the user who posted the job
    private Long userId;

    
    
}
