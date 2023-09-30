package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentDto;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentListResponse;
import com.kakao.golajuma.comment.web.dto.response.SaveCommentResponse;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	@Transactional
	public SaveCommentResponse create(SaveCommentRequest requestDto, Long voteId, Long userId) {
		// 1. 투표한 유저인지 확인 -decision이 나와야함
		// decisionRepository.findByUserIdVoteId(voteId,userId).orElseThrow(new NoDecisionException("투표
		// 후에 가능합니다.", HttpStatus.UNAUTHORIZED));

		// 저장
		CommentEntity commentEntity = requestDto.toEntity(voteId, userId);
		commentRepository.save(commentEntity);

		// return
		SaveCommentResponse response = new SaveCommentResponse(commentEntity, true, 1);
		return response;
	}

	// 페이지 구현하기 안해둠
	public ReadCommentListResponse readList(Long voteId, Long userId) {
		// 1. 투표한 유저인지 확인 -decision이 나와야함
		// decisionRepository.findByUserIdVoteId(voteId,userId).orElseThrow(new NoDecisionException("투표
		// 후에 가능합니다.", HttpStatus.UNAUTHORIZED));

		// 가져오기
		List<CommentEntity> commentEntityList = commentRepository.findByVoteId(voteId);
		boolean isOwner;
		// 2. 유저이름 가져오기 로직
		List<ReadCommentDto> readCommentDtoList = new ArrayList<>();
		for (CommentEntity commentEntity : commentEntityList) {
			Long id = commentEntity.getUserId();
			String username = "asdf"; // 데이터베이스에서 유저 닉네임 가져오기 위한 레포지토리가 들어갈 부분 - 미완성
			// 3. 주인 판별 로직
			if (userId.equals(id)) isOwner = true;
			else isOwner = false;

			readCommentDtoList.add(new ReadCommentDto(commentEntity, isOwner, username));
		}

		ReadCommentListResponse response = new ReadCommentListResponse(readCommentDtoList);

		return response;
	}

	@Transactional
	public UpdateCommentResponse update(
			UpdateCommentRequest requestDto, Long commentId, Long userId) {
		// 1. 본인의 comment인지 확인
		CommentEntity commentEntity = commentRepository.findById(commentId);
		if (commentEntity.getUserId().equals(userId))
			throw new NoOwnershipException("접근할 수 없습니다.", HttpStatus.FORBIDDEN);

		// update하기
		commentEntity.setContent(requestDto.getContent());
		commentRepository.save(commentEntity);
		UpdateCommentResponse response = new UpdateCommentResponse(commentEntity, true, 1);
		return response;
	}

	@Transactional
	public void delete(Long commentId, Long userId) {
		// 1. 본인의 comment인지 확인
		CommentEntity commentEntity = commentRepository.findById(commentId);
		if (commentEntity.getUserId().equals(userId))
			throw new NoOwnershipException("접근할 수 없습니다.", HttpStatus.FORBIDDEN);

		// 삭제로직
		commentRepository.delete(commentEntity);
	}
}
