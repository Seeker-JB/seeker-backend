package com.seeker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class Project extends BaseEntity {

    @NotBlank(message = "Project title is required")
    private String title;

    @NotBlank(message = "Project description is required")
    @Size(max = 1000, message = "Description too long")
    private String description;

    @NotBlank(message = "Technology stack is required")
    private String techStack;

    @NotBlank(message = "Project URL is required")
    @Size(max = 300)
    private String projectUrl;

    @NotBlank(message = "Role in project is required")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private UserEntity user;
}
