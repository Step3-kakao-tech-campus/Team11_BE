package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotVoteRepository extends JpaRepository<VoteEntity, Long> {
	@Query(
			"SELECT v "
					+ "FROM VoteEntity v "
					+ "JOIN OptionEntity o ON o.voteId = v.id "
					+ "JOIN DecisionEntity d ON o.id = d.optionId "
					+ "WHERE d.updatedDate >= :startTime AND d.updatedDate < :endTime AND v.deleted = false and v.voteActive = :active "
					+ "GROUP BY v.id "
					+ "ORDER BY count(d.id) DESC")
	Slice<VoteEntity> read(
			@Param("active") String active,
			@Param("endTime") LocalDateTime endTime,
			@Param("startTime") LocalDateTime startTime,
			Pageable pageable);
}
