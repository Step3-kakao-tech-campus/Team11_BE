package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = DecisionEntity.ENTITY_PREFIX)
public class DecisionEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "decision";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private Long id;

	@Column(name = ENTITY_PREFIX + "_option_id", nullable = false)
	private Long optionId;

	@Column(name = ENTITY_PREFIX + "_user_id", nullable = false)
	private Long userId;
}
