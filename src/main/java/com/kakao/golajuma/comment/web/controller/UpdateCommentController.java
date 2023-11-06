package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.comment.domain.service.UpdateCommentService;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
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
public class UpdateCommentController {

	private final UpdateCommentService updateCommentService;

	@PutMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<UpdateCommentResponse>> update(
			@PathVariable Long commentId,
			@Valid @RequestBody UpdateCommentRequest requestDto,
			@Login Long userId) {
		UpdateCommentResponse responseData =
				updateCommentService.execute(requestDto, commentId, userId);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.UPDATE);
	}
}
