package com.kakao.golajuma.auth.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UpdateEmailResponse {
	private String email;
}
