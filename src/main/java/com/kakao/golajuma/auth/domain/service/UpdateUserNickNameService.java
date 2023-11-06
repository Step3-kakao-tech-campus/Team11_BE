package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundException;
import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserNickNameRequest;
import com.kakao.golajuma.auth.web.dto.response.UpdateNickNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateUserNickNameService {

	private final UserRepository userRepository;

	public UpdateNickNameResponse execute(UpdateUserNickNameRequest requestDto, Long userId) {
		UserEntity userEntity =
				userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않은 유저입니다."));
		String newNickName = requestDto.getNickname();
		userEntity.updateNickName(newNickName);
		return new UpdateNickNameResponse(newNickName);
	}
}
