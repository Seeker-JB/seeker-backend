package com.seeker.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String requiredSkills;

    private String experience;

    private String location;

    private String technology;

    @NotBlank(message = "Description is required")
    private String description;
}
