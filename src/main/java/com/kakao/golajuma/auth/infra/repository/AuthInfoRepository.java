package com.kakao.golajuma.auth.infra.repository;

import com.kakao.golajuma.auth.infra.entity.AuthInfoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {
	Optional<AuthInfoEntity> findByUserIdAndToken(Long userId, String token);
}
