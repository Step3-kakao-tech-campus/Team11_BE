package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeleteCommentService {

	private final CommentRepository commentRepository;
	/**
	 * 댓글을 삭제한다.
	 *
	 * @param commentId 댓글 식별자
	 * @param userId 유저 식별자
	 * @throws NullPointerException 삭제하고 싶은 댓글이 이미 존재하지 않을 때
	 * @throws NoOwnershipException 해당 댓글id의 주인이 아닐 때
	 */
	@Transactional
	public void execute(Long commentId, Long userId) {
		// 1. 존재하는 댓글인지 확인
		CommentEntity commentEntity =
				commentRepository
						.findById(commentId)
						.orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

		// 2. 본인의 comment인지 확인
		if (commentEntity.isNotOwner(userId)) throw new NoOwnershipException("접근할 수 없습니다.");
		// 삭제로직
		commentEntity.delete();
	}
}
