package com.seeker.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

	private Long id;

	private String content;

	private String mediaUrl;

	private int likeCount;

	private boolean likedByCurrentUser;
}
