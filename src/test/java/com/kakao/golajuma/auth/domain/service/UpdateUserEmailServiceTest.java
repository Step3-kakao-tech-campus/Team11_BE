package com.kakao.golajuma.auth.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserEmailRequest;
import com.kakao.golajuma.auth.web.dto.response.UpdateEmailResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateUserEmailServiceTest {
	@InjectMocks private UpdateUserEmailService updateUserEmailService;
	@Mock private UserRepository userRepository;

	@Nested
	@DisplayName("유저는 이메일을 변경하는데 성공한다.")
	class update_user_email_success_case {

		@Test
		@DisplayName("유저는 이메일을 변경하는데 성공한다.")
		void update_user_email_success_test() {
			// given
			Long userId = 1L;

			UpdateUserEmailRequest requestDto = new UpdateUserEmailRequest("newemail@gmail.com");

			UserEntity userEntity = UserEntity.builder().id(1L).email("oldemail@gmail.com").build();

			when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
			// when
			UpdateEmailResponse response = updateUserEmailService.execute(requestDto, userId);
			// then
			assertThat(response.getEmail()).isEqualTo("newemail@gmail.com");
		}
	}
}
