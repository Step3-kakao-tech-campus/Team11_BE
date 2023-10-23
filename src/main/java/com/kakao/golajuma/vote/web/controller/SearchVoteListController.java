package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.SearchVoteListService;
import com.kakao.golajuma.vote.web.dto.response.SearchVoteListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchVoteListController {

	private final SearchVoteListService searchVoteListService;

	// 투표 검색 -> 투표 리스트 조회
	@GetMapping("/votes/search")
	public ApiResponse<ApiResponseBody.SuccessBody<SearchVoteListResponse>> searchVoteList(
			@Login Long userId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "current") String sort,
			@RequestParam(defaultValue = "total") String category) {
		SearchVoteListResponse responseDto =
				searchVoteListService.searchVoteList(userId, page, keyword, sort, category);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
