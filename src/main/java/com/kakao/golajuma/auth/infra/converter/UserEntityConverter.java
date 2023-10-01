package com.kakao.golajuma.auth.infra.converter;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
import com.kakao.golajuma.common.support.converter.AbstractEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityConverter implements AbstractEntityConverter<UserEntity, SaveUserRequest> {

	private final PasswordEncoder passwordEncoder;

	@Override
	public UserEntity toEntity(SaveUserRequest source) {
		return UserEntity.builder()
				.nickname(source.getNickname())
				.email(source.getEmail())
				.password(passwordEncoder.encode(source.getPassword()))
				.build();
	}
}
