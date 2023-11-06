package com.kakao.golajuma.comment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.domain.service.UpdateCommentService;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateCommentServiceTest {
	@InjectMocks private UpdateCommentService updateCommentService;

	@Mock private CommentRepository commentRepository;

	@Mock private UserRepository userRepository;

	@Test
	@DisplayName("유저가 댓글을 업데이트를 하는데 성공한다.")
	void create_comment_success_test() {
		// given
		Long userId = 1L;
		Long commentId = 1L;
		Long voteId = 1L;

		UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("new content").build();

		UserEntity userEntity = UserEntity.builder().id(1L).nickname("tester").build();

		CommentEntity commentEntity =
				CommentEntity.builder()
						.id(commentId)
						.voteId(voteId)
						.userId(userId)
						.content("old content")
						.build();

		when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
		when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
		// when
		UpdateCommentResponse response = updateCommentService.execute(requestDto, commentId, userId);
		// then
		assertThat(response.getUsername()).isEqualTo("tester");
		assertThat(response.getContent()).isEqualTo("new content");
		assertThat(response.getIsOwner()).isTrue();
	}

	@DisplayName("유저는 댓글을 업데이트 하는 것에 실패한다.")
	@Nested
	class update_comment_fail_case {
		@Test
		@DisplayName("없는 댓글을 요청했기 때문에 실패한다.")
		void not_exist_comment_update_test() {
			// given
			Long commentId = 99999999L;
			Long userId = 1L;

			UpdateCommentRequest requestDto =
					UpdateCommentRequest.builder().content("new content").build();

			// when & then
			assertThrows(
					NullPointerException.class,
					() -> updateCommentService.execute(requestDto, commentId, userId));
		}

		@Test
		@DisplayName("본인이 작성하지 않은 댓글 업데이트를 요청했기 때문에 실패한다.")
		void no_owner_update_test() {
			// given
			Long commentId = 1L;
			Long userId = 1L;
			Long wrongUserId = 2L;

			UpdateCommentRequest requestDto =
					UpdateCommentRequest.builder().content("new content").build();

			CommentEntity commentEntity =
					CommentEntity.builder()
							.id(commentId)
							.voteId(1L)
							.userId(userId)
							.content("content1")
							.build();
			// 존재하는지 확인하면서 반환
			when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
			// when & then
			assertThrows(
					NoOwnershipException.class,
					() -> updateCommentService.execute(requestDto, commentId, wrongUserId));
		}
	}
}
