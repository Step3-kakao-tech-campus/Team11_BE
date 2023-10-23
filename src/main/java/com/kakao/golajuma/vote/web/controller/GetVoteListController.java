package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.GetVoteListService;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetVoteListController {

	private final GetVoteListService getVoteListService;

	// 투표 조회 - 메인페이지, 완료된 페이지
	@GetMapping("/votes")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MainAndFinishPage>>
			getVoteList(
					@Login Long userId,
					@RequestParam(defaultValue = "0") int page,
					@RequestParam(defaultValue = "current") String sort,
					@RequestParam(defaultValue = "continue") String active,
					@RequestParam(defaultValue = "total") String category) {
		GetVoteListResponse.MainAndFinishPage responseDto =
				getVoteListService.getVoteList(userId, page, sort, active, category);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}

	// 투표 리스트 조회 - 마이페이지 참여한 투표
	@GetMapping("/users/votes/participate")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MyPage>>
			getVoteListInMyPageByParticipate(@Login Long userId) {
		GetVoteListResponse.MyPage responseDto =
				getVoteListService.getVoteListInMyPageByParticipate(userId);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}

	// 투표 리스트 조회 - 마이페이지 올린 투표
	@GetMapping("/users/votes/ask")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MyPage>>
			getVoteListInMyPageByAsk(@Login Long userId) {
		GetVoteListResponse.MyPage responseDto = getVoteListService.getVoteListInMyPageByAsk(userId);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
