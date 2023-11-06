package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.CreateVoteService;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CreateVoteController {
	private final CreateVoteService createVoteService;

	// 투표 생성
	@PostMapping("/votes")
	public ApiResponse<SuccessBody<CreateVoteResponse>> createVote(
			@RequestBody @Valid CreateVoteRequest requestDto, @Login Long userId) {
		CreateVoteResponse responseDto = createVoteService.createVote(requestDto, userId);
		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.CREATE);
	}
}
