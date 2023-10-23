package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.infra.converter.AuthInfoEntityConverter;
import com.kakao.golajuma.auth.infra.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.infra.repository.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveTokenService {
	private final AuthInfoRepository authInfoRepository;
	private final AuthInfoEntityConverter authInfoEntityConverter;

	@Transactional
	public void execute(Long userId, String token) {
		AuthInfoEntity authInfoEntity = authInfoEntityConverter.from(userId, token);
		authInfoRepository.save(authInfoEntity);
	}
}
