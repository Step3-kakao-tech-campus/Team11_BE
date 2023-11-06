package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UpdateCommentService {

	private final CommentRepository commentRepository;

	private final UserRepository userRepository;
	/**
	 * 댓글을 삭제한다.
	 *
	 * @param requestDto 바꾸고 싶은 내용을 담고있는 객체
	 * @param commentId 댓글 식별자
	 * @param userId 유저 식별자
	 * @return 업데이트한 댓글의 정보
	 * @throws NullPointerException 삭제하고 싶은 댓글이 이미 존재하지 않을 때
	 * @throws NoOwnershipException 해당 댓글id의 주인이 아닐 때
	 */
	@Transactional
	public UpdateCommentResponse execute(
			UpdateCommentRequest requestDto, Long commentId, Long userId) {

		// 1. 존재하는 댓글인지 확인
		CommentEntity commentEntity =
				commentRepository
						.findById(commentId)
						.orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

		// 2. 본인의 comment인지 확인
		if (commentEntity.isNotOwner(userId)) throw new NoOwnershipException("접근할 수 없습니다.");

		// repository update >> entity update
		String newContent = requestDto.getContent();

		commentEntity.updateContent(newContent);

		String username = getUsername(userId);

		return new UpdateCommentResponse(commentEntity, true, username);
	}

	private String getUsername(Long userId) {
		UserEntity userEntity =
				userRepository
						.findById(userId)
						.orElseThrow(() -> new NullPointerException("존재하지 않는 유저입니다."));
		return userEntity.getNickname();
	}
}
