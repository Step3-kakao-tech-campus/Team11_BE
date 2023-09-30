package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.VoteService;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class VoteRestController {
	private final VoteService voteService;

	// 투표 생성
	@PostMapping("/votes")
	public ApiResponse<SuccessBody<CreateVoteResponse>> createVote(
			@RequestBody CreateVoteRequest voteDTO) {
		CreateVoteResponse responseDto = voteService.createVote(voteDTO);
		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.CREATE);
	}
}
