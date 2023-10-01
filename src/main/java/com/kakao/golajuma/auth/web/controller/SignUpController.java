package com.kakao.golajuma.auth.web.controller;

import com.kakao.golajuma.auth.domain.service.SaveUserService;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
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
public class SignUpController {
	private final SaveUserService saveUserService;

	@PostMapping("/signup")
	public ApiResponse<SuccessBody<Void>> signUp(@RequestBody @Valid SaveUserRequest request) {
		saveUserService.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}
}
