package com.seeker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {

    @NotBlank(message = "Project title is required")
    private String title;

    @NotBlank(message = "Project description is required")
    @Size(max = 1000, message = "Description too long")
    private String description;

    @NotBlank(message = "Technology stack is required")
    private String techStack;

    @Size(max = 300, message = "Project URL too long")
    private String projectUrl;

   
}
