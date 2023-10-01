package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.DuplicateException;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.supplier.NicknameSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidNicknameService {
	private final UserRepository userRepository;

	public void execute(NicknameSupplier supplier) {
		boolean exists = userRepository.existsByNickname(supplier.getNickname());
		if (exists) {
			throw new DuplicateException("중복되는 닉네임입니다");
		}
	}
}
