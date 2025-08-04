package com.seeker.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessProfileResponseDTO extends BaseDTO {

    private String businessName;

    private String description;

    private String contactEmail;

    private String website;

    private String address;

    private String logoUrl;

    private Long userId;  // Optionally include this if needed for response
}
