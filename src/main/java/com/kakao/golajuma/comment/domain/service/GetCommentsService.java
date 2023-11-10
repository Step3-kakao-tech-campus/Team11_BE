package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.CommentDto;
import com.kakao.golajuma.comment.web.dto.response.GetCommentsResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetCommentsService {

	private final CommentRepository commentRepository;

	private final GetUserNameService getUserNameService;
	/**
	 * 지정한 투표에 해당하는 댓글 리스트를 호출한다.
	 *
	 * @param voteId 투표 식별자
	 * @param userId 유저 식별자
	 * @return 해당 투표의 댓글리스트
	 * @throws NullPointerException 유저 정보가 존재하지 않을 때
	 */
	public GetCommentsResponse execute(Long voteId, Long userId) {
		// 가져오기
		List<CommentEntity> commentEntityList = commentRepository.findByVoteId(voteId);

		List<CommentDto> getCommentDtoList =
				commentEntityList.stream()
						.map(
								commentEntity -> {
									String username = getUserNameService.execute(commentEntity.getUserId());
									boolean isOwner = commentEntity.isOwner(userId);
									return new CommentDto(commentEntity, isOwner, username);
								})
						.collect(Collectors.toList());

		return new GetCommentsResponse(getCommentDtoList);
	}
}