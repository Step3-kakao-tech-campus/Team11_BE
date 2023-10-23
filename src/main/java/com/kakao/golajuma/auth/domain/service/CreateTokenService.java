package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.infra.converter.AuthInfoEntityConverter;
import com.kakao.golajuma.auth.infra.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.infra.repository.AuthInfoRepository;
import com.kakao.golajuma.auth.web.dto.converter.TokenConverter;
import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateTokenService {
	private final TokenProvider tokenProvider;
	private final TokenConverter tokenConverter;
	private final TokenResolver tokenResolver;
	private final AuthInfoRepository authInfoRepository;
	private final AuthInfoEntityConverter authInfoEntityConverter;

	@Transactional
	public TokenResponse execute(final Long userId) {
		String accessToken = tokenProvider.createAccessToken(userId);
		String refreshToken = tokenProvider.createRefreshToken(userId);

		saveToken(userId, refreshToken);

		return tokenConverter.from(
				accessToken, tokenResolver.getExpiredDate(accessToken), refreshToken);
	}

	private void saveToken(Long userId, String token) {
		AuthInfoEntity authInfoEntity = authInfoEntityConverter.from(userId, token);
		authInfoRepository.save(authInfoEntity);
	}
}
