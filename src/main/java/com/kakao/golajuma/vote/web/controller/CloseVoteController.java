package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.CloseVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CloseVoteController {
	private final CloseVoteService closeVoteService;

	// 투표 마감
	@PatchMapping("/vote/{voteId}/close")
	public ApiResponse<ApiResponseBody.SuccessBody<Void>> closeVote(
			@PathVariable Long voteId, @Login Long userId) {
		closeVoteService.closeVote(voteId, userId);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.UPDATE);
	}
}
