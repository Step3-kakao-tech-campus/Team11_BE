package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.infra.converter.UserEntityConverter;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaveUserService {

	private final UserRepository userRepository;
	private final UserEntityConverter entityConverter;
	private final ValidEmailService validEmailService;
	private final ValidNicknameService validNicknameService;

	@Transactional
	public void execute(final SaveUserRequest source) {
		valid(source);
		userRepository.save(entityConverter.toEntity(source));
	}

	private void valid(final SaveUserRequest source) {
		validEmailService.execute(source);
		validNicknameService.execute(source);
	}
}
