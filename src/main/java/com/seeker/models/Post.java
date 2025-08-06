package com.seeker.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@Column(name = "content", nullable = false, length = 2000)
	@NotBlank(message = "Content cannot be blank")
	private String content;

	@Column(name = "media_url", length = 500)
	private String mediaUrl; // optional: image/video link

	@Column(name = "like_count", nullable = false)
	private int likeCount = 0; // default 0

	// Unidirectional Realtionship
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Like> likes = new ArrayList<>();

	// Helper methods for maintaining the relationship
	public void addLike(Like like) {
		likes.add(like);
		like.setPost(this);
		this.likeCount = likes.size(); // keep likeCount in sync
	}

	public void removeLike(Like like) {
		likes.remove(like);
		like.setPost(null);
		this.likeCount = likes.size(); // keep likeCount in sync
	}
}
