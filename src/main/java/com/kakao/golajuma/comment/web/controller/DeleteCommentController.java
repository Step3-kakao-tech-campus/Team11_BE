package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.comment.domain.service.DeleteCommentService;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/{voteId}/comments")
public class DeleteCommentController {

	private final DeleteCommentService deleteCommentService;

	@DeleteMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<Void>> delete(
			@PathVariable Long commentId, @Login Long userId) {
		deleteCommentService.execute(commentId, userId);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
	}
}
