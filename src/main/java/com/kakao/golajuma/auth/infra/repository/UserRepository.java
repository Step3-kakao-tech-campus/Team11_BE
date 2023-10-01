package com.kakao.golajuma.auth.infra.repository;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
