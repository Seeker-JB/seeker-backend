package com.seeker.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationQualificationResponseDto {

    private Long id;

    private String degree;

    private String stream;

    private String university;

    private String grade;

    private int passingYear;

    private Long userId;
}
