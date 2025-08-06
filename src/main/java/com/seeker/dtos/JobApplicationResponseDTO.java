package com.seeker.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String resumeUrl;

    private Long userId;
}
