package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.CommentDto;
import com.kakao.golajuma.comment.web.dto.response.GetCommentListResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetCommentListService {

	private final CommentRepository commentRepository;

	private final UserRepository userRepository;

	public GetCommentListResponse execute(Long voteId, Long userId) {
		// 가져오기
		List<CommentEntity> commentEntityList = commentRepository.findByVoteId(voteId);

		int commentCount = commentEntityList.size();

		List<CommentDto> getCommentDtoList =
				commentEntityList.stream()
						.map(
								commentEntity -> {
									String username = getUsername(userId);
									boolean isOwner = commentEntity.isOwner(userId);
									return new CommentDto(commentEntity, isOwner, username);
								})
						.collect(Collectors.toList());

		return new GetCommentListResponse(getCommentDtoList, commentCount);
	}

	private String getUsername(Long userId) {
		UserEntity userEntity =
				userRepository
						.findById(userId)
						.orElseThrow(() -> new NullPointerException("존재하지 않는 유저입니다."));
		return userEntity.getNickname();
	}
}