package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.DecisionService;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/participate")
public class DecisionController {
	private final DecisionService decisionService;

	@PostMapping("/options/{optionId}")
	public ApiResponse<SuccessBody<DecisionResponse>> createVote(
			@PathVariable Long optionId, @Login Long userId) {
		DecisionResponse response = decisionService.createDecision(userId, optionId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
	}

	@DeleteMapping("/options/{optionId}")
	public ApiResponse<SuccessBody<DecisionResponse>> deleteVote(
			@PathVariable Long optionId, @Login Long userId) {
		DecisionResponse response = decisionService.deleteVote(userId, optionId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.DELETE);
	}
}
