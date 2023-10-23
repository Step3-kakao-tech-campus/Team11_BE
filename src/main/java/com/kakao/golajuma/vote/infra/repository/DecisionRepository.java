package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<DecisionEntity, Long> {
	Optional<DecisionEntity> findByUserIdAndOptionId(Long userId, Long OptionId);

	boolean existsByUserIdAndOptionId(Long userId, Long optionId);
}
