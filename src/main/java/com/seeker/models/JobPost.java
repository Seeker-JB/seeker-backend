package com.seeker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@Table(name = "job_post")
public class JobPost extends BaseEntity {

    @Column(name = "title", nullable = false, length = 255)
    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "company_name", nullable = false, length = 255)
    @NotBlank(message = "Company name is required")
    private String companyName;

    @Column(name = "required_skills", length = 500)
    private String requiredSkills;

    @Column(name = "experience", length = 100)
    private String experience;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "technology", length = 255)
    private String technology;

    @Column(name = "description", nullable = false, length = 2000)
    @NotBlank(message = "Description is required")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // foreign key column in job_post table
    private UserEntity user;
}
