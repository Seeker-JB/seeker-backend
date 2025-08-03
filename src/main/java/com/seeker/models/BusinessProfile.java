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
public class BusinessProfile extends BaseEntity {

    @Column(name = "business_name", nullable = false, length = 255)
    @NotBlank(message = "Business name is required")
    private String businessName;

    @Column(name = "description", nullable = false, length = 1000)
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description too long")
    private String description;

    @Column(name = "contact_email", nullable = false, length = 255)
    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;

    @Column(name = "website", nullable = false, length = 255)
    @NotBlank(message = "Website is required")
    private String website;

    @Column(name = "address", nullable = false, length = 500)
    @NotBlank(message = "Address is required")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
