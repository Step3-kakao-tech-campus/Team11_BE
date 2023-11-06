package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateCommentService {

	private final CommentRepository commentRepository;

	private final UserRepository userRepository;

	/**
	 * 댓글을 생성한다.
	 *
	 * @param requestDto 생성할 댓글을 포함하고 있음
	 * @param voteId 투표 식별자
	 * @param userId 유저 식별자
	 * @return 생성한 댓글의 정보
	 * @throws NullPointerException 존재하지 않은 유저가 생성을 요청했을 때
	 */
	public CreateCommentResponse execute(CreateCommentRequest requestDto, Long voteId, Long userId) {
		// 저장
		CommentEntity commentEntity = requestDto.toEntity(voteId, userId);
		commentRepository.save(commentEntity);
		// return
		String username = getUsername(userId);

		return new CreateCommentResponse(commentEntity, true, username);
	}

	private String getUsername(Long userId) {
		UserEntity userEntity =
				userRepository
						.findById(userId)
						.orElseThrow(() -> new NullPointerException("존재하지 않는 유저입니다."));
		return userEntity.getNickname();
	}
}
