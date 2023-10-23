package com.kakao.golajuma.auth.infra.entity;

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
@Table(name = UserEntity.ENTITY_PREFIX)
public class UserEntity extends BaseEntity {
	public static final String ENTITY_PREFIX = "user";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private Long id;

	@Column(name = ENTITY_PREFIX + "_nickname", nullable = false)
	private String nickname;

	@Column(name = ENTITY_PREFIX + "_email", nullable = false)
	private String email;

	@Column(name = ENTITY_PREFIX + "_password", nullable = false)
	private String password;
}
