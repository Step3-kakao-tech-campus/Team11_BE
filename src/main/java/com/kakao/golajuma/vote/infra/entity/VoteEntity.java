package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.common.BaseEntity;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@ToString
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = VoteEntity.ENTITY_PREFIX)
public class VoteEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "vote";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = ENTITY_PREFIX + "_total_count", nullable = false)
	@ColumnDefault("0")
	private int voteTotalCount;

	@Column(name = ENTITY_PREFIX + "_title", length = 256, nullable = false)
	private String voteTitle;

	@Column(name = ENTITY_PREFIX + "_content", length = 1000)
	private String voteContent;

	@Enumerated(EnumType.STRING)
	@Column(name = ENTITY_PREFIX + "_category", nullable = false)
	private Category category;

	@Column(name = ENTITY_PREFIX + "_end_date", nullable = false)
	private LocalDateTime voteEndDate;

	@Column(name = ENTITY_PREFIX + "_type")
	private String voteType;

	public Active checkActive() {
		LocalDateTime now = LocalDateTime.now();
		if (voteEndDate.isBefore(now)) {
			return Active.COMPLETE;
		}
		return Active.CONTINUE;
	}

	public boolean isOn() {
		if (checkActive() == Active.CONTINUE) {
			return true;
		}
		return false;
	}

	public static VoteEntity createEntity(CreateVoteRequest request, Long userId) {
		VoteEntity vote =
				VoteEntity.builder()
						.userId(userId)
						.category(Category.findCategory(request.getCategory()))
						.voteTitle(request.getTitle())
						.voteContent(request.getContent())
						.voteType("null")
						.voteEndDate(LocalDateTime.now().plusMinutes(request.getTimeLimit()))
						.build();
		return vote;
	}

	public boolean isOwner(Long userId) {
		return userId == this.getUserId();
	}

	public boolean isComplete() {
		if (checkActive().equals(Active.COMPLETE)) {
			return true;
		}
		return false;
	}

	public void updateCount() {
		this.voteTotalCount += 1;
	}

	public void decreaseCount() {
		this.voteTotalCount -= 1;
	}

	public void close() {
		this.voteEndDate = LocalDateTime.now();
	}
}
