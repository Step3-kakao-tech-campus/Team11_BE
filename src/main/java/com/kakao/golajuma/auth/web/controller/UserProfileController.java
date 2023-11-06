package com.kakao.golajuma.auth.web.controller;

import com.kakao.golajuma.auth.domain.service.ReadUserProfileService;
import com.kakao.golajuma.auth.domain.service.UpdateUserEmailService;
import com.kakao.golajuma.auth.domain.service.UpdateUserNickNameService;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserEmailRequest;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserNickNameRequest;
import com.kakao.golajuma.auth.web.dto.response.UpdateEmailResponse;
import com.kakao.golajuma.auth.web.dto.response.UpdateNickNameResponse;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.auth.web.support.Login;
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
@RequestMapping("/users")
public class UserProfileController {

	private final ReadUserProfileService readUserProfileService;

	private final UpdateUserEmailService updateUserEmailService;

	private final UpdateUserNickNameService updateUserNickNameService;

	@GetMapping("/profile")
	public ApiResponse<ApiResponseBody.SuccessBody<UserProfileResponse>> readUserProfile(
			@Login Long userId) {

		UserProfileResponse responseData = readUserProfileService.execute(userId);

		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.GET);
	}

	@PatchMapping("/nickname")
	public ApiResponse<ApiResponseBody.SuccessBody<UpdateNickNameResponse>> updateNickName(
			@RequestBody @Valid UpdateUserNickNameRequest request, @Login Long userId) {

		UpdateNickNameResponse responseData = updateUserNickNameService.execute(request, userId);

		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.UPDATE);
	}

	@PatchMapping("/email")
	public ApiResponse<ApiResponseBody.SuccessBody<UpdateEmailResponse>> updateEmail(
			@RequestBody @Valid UpdateUserEmailRequest request, @Login Long userId) {

		UpdateEmailResponse responseData = updateUserEmailService.execute(request, userId);

		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.UPDATE);
	}
}
