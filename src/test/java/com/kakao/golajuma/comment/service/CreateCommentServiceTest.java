package com.kakao.golajuma.comment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.domain.service.CreateCommentService;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCommentServiceTest {
	@InjectMocks private CreateCommentService createCommentService;

	@Mock private CommentRepository commentRepository;

	@Mock private UserRepository userRepository;

	@Test
	@DisplayName("유저가 새로운 댓글을 저장하는데 성공한다.")
	void create_comment_success_test() {
		// given
		Long userId = 1L;
		Long voteId = 1L;

		CreateCommentRequest requestDto = CreateCommentRequest.builder().content("contents").build();

		UserEntity userEntity = UserEntity.builder().id(1L).nickname("tester").build();

		when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

		// when
		CreateCommentResponse response = createCommentService.execute(requestDto, voteId, userId);
		// then

		verify(commentRepository).save(any(CommentEntity.class));

		assertThat(response.getUsername()).isEqualTo("tester");
		assertThat(response.getContent()).isEqualTo("contents");
		assertThat(response.getIsOwner()).isTrue();
	}

	@DisplayName("유저는 새로운 댓글을 저장하는데 실패한다.")
	@Nested
	class create_comment_fail_case {
		@Test
		@DisplayName("유저 데이터가 존재하지 않기 때문에 실패한다.")
		void not_exist_user_create_test() {
			// given
			Long userId = 99999999L;
			Long voteId = 1L;

			CreateCommentRequest requestDto = CreateCommentRequest.builder().content("contents").build();

			// when & then
			assertThrows(
					NullPointerException.class,
					() -> createCommentService.execute(requestDto, voteId, userId));
		}
	}
}
