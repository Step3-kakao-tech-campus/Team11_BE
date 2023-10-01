package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundException;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.converter.TokenConverter;
import com.kakao.golajuma.auth.web.dto.request.LoginUserRequest;
import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService {

	private final TokenProvider tokenProvider;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenConverter tokenConverter;
	private final TokenResolver tokenResolver;
	private final TokenService tokenService;

	@Transactional
	public TokenResponse execute(final LoginUserRequest request) {
		UserEntity userEntity =
				userRepository
						.findByEmail(request.getEmail())
						.orElseThrow(() -> new NotFoundException("존재하지 않는 이메일입니다."));

		validPassword(request.getPassword(), userEntity);

		String accessToken = tokenProvider.createAccessToken(userEntity.getId());
		String refreshToken = tokenProvider.createRefreshToken(userEntity.getId());

		tokenService.execute(userEntity.getId(), refreshToken);

		return tokenConverter.from(
				accessToken, tokenResolver.getExpiredDate(accessToken), refreshToken);
	}

	private void validPassword(final String requestPassword, final UserEntity userEntity) {
		if (!matchPassword(requestPassword, userEntity.getPassword())) {
			throw new NotFoundException("존재하지 않는 비밀번호입니다");
		}
	}

	private boolean matchPassword(final String requestPassword, final String password) {
		return passwordEncoder.matches(requestPassword, password);
	}
}
