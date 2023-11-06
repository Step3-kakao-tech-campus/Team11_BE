package com.kakao.golajuma.comment.infra.entity;

import static com.kakao.golajuma.comment.infra.entity.CommentEntity.ENTITY_PREFIX;

import com.kakao.golajuma.common.BaseEntity;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = ENTITY_PREFIX)
public class CommentEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "comment";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", unique = true, nullable = false)
	private Long id;

	@Column(name = ENTITY_PREFIX + "_vote_id", length = 15, nullable = false)
	private Long voteId;

	@Column(name = ENTITY_PREFIX + "_user_id", length = 15, nullable = false)
	private Long userId;

	@Column(name = ENTITY_PREFIX + "_content", length = 255, nullable = false)
	private String content;

	public void updateContent(String content) {
		this.content = content;
	}

	public Boolean isOwner(Long userId) {
		return this.userId.equals(userId);
	}

	public Boolean isNotOwner(Long userId) {
		return !this.userId.equals(userId);
	}
}
