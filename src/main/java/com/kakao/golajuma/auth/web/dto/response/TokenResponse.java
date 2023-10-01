package com.kakao.golajuma.auth.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenResponse implements AbstractResponseDto {

	private String accessToken;
	private Date expiredTime;
	@JsonIgnore private String refreshToken;
}
