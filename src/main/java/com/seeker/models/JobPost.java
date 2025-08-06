package com.seeker.models;

import java.util.List;

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
    
    @Column(name = "description", nullable = false, length = 2000)
    @NotBlank(message = "Description is required")
    private String description;

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
    
    
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<JobApplication> jobApplications;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // foreign key column in job_post table
    private UserEntity user;
    
    
    
    public void addJobApplication(JobApplication application) {
        jobApplications.add(application);
        application.setJobPost(this);
    }

    // ✅ Helper Method - Remove Application
    public void removeJobApplication(JobApplication application) {
        jobApplications.remove(application);
        application.setJobPost(null);
    }
}





