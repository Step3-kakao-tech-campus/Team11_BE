package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.common.BaseEntity;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@ToString
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "vote_" + OptionEntity.ENTITY_PREFIX)
public class OptionEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "option";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private Long id;

	@Column(name = ENTITY_PREFIX + "_vote_id", nullable = false)
	private Long voteId;

	@Column(name = ENTITY_PREFIX + "_name", nullable = false, length = 200)
	private String optionName;

	@Column(name = ENTITY_PREFIX + "_image")
	private String optionImage;

	@Column(name = ENTITY_PREFIX + "_count")
	@ColumnDefault("0")
	private int optionCount;

	public static OptionEntity createEntity(CreateVoteRequest.OptionDto request, Long voteId) {
		return OptionEntity.builder()
				.voteId(voteId)
				.optionName(request.getName())
				.optionImage(null)
				.build();
	}

	public static OptionEntity createEntityWithImage(
			CreateVoteRequest.OptionDto request, String imagePath, Long voteId) {
		return OptionEntity.builder()
				.voteId(voteId)
				.optionName(request.getName())
				.optionImage(imagePath)
				.build();
	}

	public void updateCount() {
		this.optionCount += 1;
	}

	public void decreaseCount() {
		this.optionCount -= 1;
	}
}
