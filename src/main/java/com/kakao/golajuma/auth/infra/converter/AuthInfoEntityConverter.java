package com.kakao.golajuma.auth.infra.converter;

import com.kakao.golajuma.auth.infra.entity.AuthInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthInfoEntityConverter {

	public AuthInfoEntity from(Long userId, String refreshToken) {
		return AuthInfoEntity.builder().userId(userId).token(refreshToken).build();
	}
}
