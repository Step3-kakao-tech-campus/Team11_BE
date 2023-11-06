package com.kakao.golajuma.auth.web.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserEmailRequest {
	@NotBlank private String email;
}
