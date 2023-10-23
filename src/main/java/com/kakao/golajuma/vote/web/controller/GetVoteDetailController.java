package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.GetVoteDetailService;
import com.kakao.golajuma.vote.web.dto.response.GetVoteDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetVoteDetailController {
	private final GetVoteDetailService getVoteDetailService;

	@GetMapping("/vote/{voteId}")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteDetailResponse>> getVoteDetail(
			@PathVariable int voteId, @Login Long userId) {
		GetVoteDetailResponse responseDto = getVoteDetailService.getVoteDetail(voteId, userId);
		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
