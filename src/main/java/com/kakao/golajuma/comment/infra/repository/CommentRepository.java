package com.kakao.golajuma.comment.infra.repository;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	@Query("Select c from CommentEntity c where c.id = :commentId")
	CommentEntity findById(@Param("commentId") long commentId);

	@Query("select c from CommentEntity c where c.voteId = :voteId")
	List<CommentEntity> findByVoteId(@Param("voteId") long voteId);
}
