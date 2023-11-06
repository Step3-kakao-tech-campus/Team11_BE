package com.kakao.golajuma.auth.web.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserNickNameRequest {
	@NotBlank private String nickname;
}
