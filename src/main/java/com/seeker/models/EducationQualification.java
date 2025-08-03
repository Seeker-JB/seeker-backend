package com.seeker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@Table(name = "education_qualification")
public class EducationQualification extends BaseEntity {


    @Column(name = "degree", nullable = false, length = 100)
    private String degree;

    @Column(name = "stream", nullable = false, length = 100)
    private String stream;

    @Column(name = "university", nullable = false, length = 255)
    private String university;

    @Column(name = "grade", nullable = true, length = 50)
    private String grade;

    @Column(name = "passing_year", nullable = false)
    private int passingYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // FK column in education table
    private UserEntity user;
}
