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
@EqualsAndHashCode(exclude = "user")
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "title", nullable = false, length = 255)
    @NotBlank(message = "Project title is required")
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    @NotBlank(message = "Project description is required")
    @Size(max = 1000, message = "Description too long")
    private String description;

    @Column(name = "tech_stack", nullable = false, length = 255)
    @NotBlank(message = "Technology stack is required")
    private String techStack;

    @Column(name = "project_url", nullable = false, length = 300)
    @Size(max = 300, message = "Project URL too long")
    private String projectUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // foreign key column
    private UserEntity user;
}
