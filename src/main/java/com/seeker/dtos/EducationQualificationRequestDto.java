package com.seeker.dtos;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationQualificationRequestDto {

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Stream is required")
    private String stream;

    @NotBlank(message = "University is required")
    private String university;

    private String grade;

    @Min(value = 1900, message = "Invalid year")
    @Max(value = 2100, message = "Invalid year")
    private int passingYear;
}
