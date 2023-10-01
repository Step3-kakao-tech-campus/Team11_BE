package com.kakao.golajuma.auth.web.controller;

import com.kakao.golajuma.auth.domain.service.ValidEmailService;
import com.kakao.golajuma.auth.domain.service.ValidNicknameService;
import com.kakao.golajuma.auth.web.dto.request.ValidEmailRequest;
import com.kakao.golajuma.auth.web.dto.request.ValidNicknameRequest;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class ValidController {
	private final ValidEmailService validEmailService;
	private final ValidNicknameService validNicknameService;

	@PostMapping("/email-check")
	public ApiResponse<SuccessBody<Void>> validEmail(@RequestBody @Valid ValidEmailRequest request) {
		validEmailService.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}

	@PostMapping("/nickname-check")
	public ApiResponse<SuccessBody<Void>> validNickname(
			@RequestBody @Valid ValidNicknameRequest request) {
		validNicknameService.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}
}
