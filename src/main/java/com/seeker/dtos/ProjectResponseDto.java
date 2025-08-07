package com.seeker.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {

    private Long id;

    private String title;

    private String description;

    private String techStack;

    private String projectUrl;

    private Long userId;

}
