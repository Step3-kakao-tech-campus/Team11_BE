package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.common.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = VoteEntity.ENTITY_PREFIX + "_tb")
public class VoteEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "vote";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private long id;

	@Column(name = "user_id", nullable = false)
	private long userId;

	@Column(name = ENTITY_PREFIX + "_title", length = 256, nullable = false)
	private String voteTitle;

	@Column(name = ENTITY_PREFIX + "_content", length = 1000)
	private String voteContent;

	@Enumerated(EnumType.STRING)
	@Column(name = ENTITY_PREFIX + "_category", nullable = false)
	private Category category;

	@Column(name = ENTITY_PREFIX + "_end_date", nullable = false)
	private LocalDateTime voteEndDate;

	@Builder.Default
	@Column(name = ENTITY_PREFIX + "_active", nullable = false)
	private String voteActive = "continue";

	@Column(name = ENTITY_PREFIX + "_type")
	private String voteType;

	@Builder
	public VoteEntity(
			long id,
			long userId,
			Category category,
			String voteTitle,
			String voteContent,
			LocalDateTime voteEndDate,
			String voteType) {
		this.id = id;
		this.userId = userId;
		this.category = category;
		this.voteTitle = voteTitle;
		this.voteContent = voteContent;
		this.voteEndDate = voteEndDate;
		this.voteType = voteType;
	}
}
