package com.seeker.dtos;

import com.seeker.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProfileResponseDto {

	private Long id;
    private String name;
    private String email;
    private Role role;

    private String profilePictureUrl;
    private String description;
    private String city;
    private String state;
    private String country;
    private String website;

}
