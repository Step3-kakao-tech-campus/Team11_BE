package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.ReadHotVoteService;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetHotVoteListController {
	private final ReadHotVoteService readHotVotesService;
	// 투표 생성
	@GetMapping("/votes/hot")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MainAndFinishPage>>
			readHotVotes(@Login Long userId, @RequestParam(defaultValue = "continue") String active) {
		GetVoteListResponse.MainAndFinishPage responseDto = readHotVotesService.read(userId, active);
		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
