package com.kakao.golajuma.comment.infra.repository;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	// 개개인의 댓글을 가져오기
	@Query("Select c from CommentEntity c where c.id = :commentId and c.deleted = false")
	Optional<CommentEntity> findById(@Param("commentId") Long commentId);
	// 투표에 따른 댓글 리스트 가져오기
	@Query("select c from CommentEntity c where c.voteId = :voteId and c.deleted = false")
	List<CommentEntity> findByVoteId(@Param("voteId") long voteId);

	@Query("select c from CommentEntity c where c.userId = :userId and c.deleted = false")
	List<CommentEntity> findByUserId(@Param("userId") long userId);
}
