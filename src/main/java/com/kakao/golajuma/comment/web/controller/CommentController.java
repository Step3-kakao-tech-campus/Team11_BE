package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.comment.domain.service.CommentService;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentListResponse;
import com.kakao.golajuma.comment.web.dto.response.SaveCommentResponse;
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
public class CommentController {
	CommentService commentService;

	@GetMapping
	public ApiResponse<ApiResponseBody.SuccessBody<ReadCommentListResponse>> readList(
			@PathVariable Long voteId) {
		ReadCommentListResponse responseData = commentService.readList(voteId, 1L);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.CREATE);
	}

	@PostMapping
	public ApiResponse<ApiResponseBody.SuccessBody<SaveCommentResponse>> create(
			@PathVariable Long voteId, @Valid @RequestBody SaveCommentRequest requestDto) {
		SaveCommentResponse responseData = commentService.create(requestDto, voteId, 1L);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.CREATE);
	}

	@PutMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<UpdateCommentResponse>> update(
			@PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequest requestDto) {
		UpdateCommentResponse responseData = commentService.update(requestDto, commentId, 1L);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.CREATE);
	}

	@DeleteMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<Void>> delete(@PathVariable Long commentId) {
		commentService.delete(commentId, 1L);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.CREATE);
	}
}
