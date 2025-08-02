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
@Table
public class EducationQualification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;         
    private String stream;         
    private String university;     
    private String grade;          
    private int passingYear;       

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // FK in education table, referencing UserEntity
    private UserEntity user;
}
