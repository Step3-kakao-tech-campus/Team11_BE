package com.kakao.golajuma.auth.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserNickNameRequest;
import com.kakao.golajuma.auth.web.dto.response.UpdateNickNameResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateUserNickNameServiceTest {
	@InjectMocks private UpdateUserNickNameService updateUserNickNameService;
	@Mock private UserRepository userRepository;

	@Nested
	@DisplayName("유저는 닉네임을 변경하는데 성공한다.")
	class update_user_nickname_success_case {

		@Test
		@DisplayName("유저는 닉네임을 변경하는데 성공한다.")
		void update_user_nickname_success_test() {
			// given
			Long userId = 1L;

			UpdateUserNickNameRequest requestDto = new UpdateUserNickNameRequest("newNickName");

			UserEntity userEntity = UserEntity.builder().id(1L).nickname("oldNickName").build();

			when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
			// when
			UpdateNickNameResponse response = updateUserNickNameService.execute(requestDto, userId);
			// then
			assertThat(response.getNickName()).isEqualTo("newNickName");
		}
	}
}
