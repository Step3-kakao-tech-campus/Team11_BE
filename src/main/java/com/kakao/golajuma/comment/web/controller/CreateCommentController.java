package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.comment.domain.service.CreateCommentService;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/{voteId}/comments")
public class CreateCommentController {

	private final CreateCommentService createCommentService;

	@PostMapping
	public ApiResponse<ApiResponseBody.SuccessBody<CreateCommentResponse>> create(
			@PathVariable Long voteId,
			@Valid @RequestBody CreateCommentRequest requestDto,
			@Login Long userId) {
		CreateCommentResponse responseData = createCommentService.execute(requestDto, voteId, userId);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.CREATE);
	}
}
