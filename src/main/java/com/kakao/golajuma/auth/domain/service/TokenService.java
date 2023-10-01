package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.model.RefreshToken;
import com.kakao.golajuma.auth.infra.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional
	public void execute(Long userId, String token) {
		RefreshToken refreshToken = RefreshToken.builder().refreshToken(token).userId(userId).build();
		refreshTokenRepository.save(refreshToken);
	}
}
