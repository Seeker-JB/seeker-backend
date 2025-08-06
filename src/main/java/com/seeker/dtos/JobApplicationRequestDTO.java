package com.seeker.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationRequestDTO {

    @NotBlank(message = "Applicant name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    private MultipartFile resume;

    @NotNull(message = "Job Post ID is required")
    private Long jobPostId;
}
