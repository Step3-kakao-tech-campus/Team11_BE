package com.kakao.golajuma.comment.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.domain.service.DeleteCommentService;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCommentServiceTest {
	@InjectMocks private DeleteCommentService deleteCommentService;

	@Mock private CommentRepository commentRepository;

	@Test
	@DisplayName("유저가 자신의 댓글을 삭제하는데 성공한다.")
	void delete_comment_success_test() {
		// given
		Long commentId = 1L;
		Long userId = 1L;

		CommentEntity commentEntity = mock(CommentEntity.class);
		// 존재하는지 확인하면서 반환
		when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
		// when
		deleteCommentService.execute(commentId, userId);
		// then
		verify(commentEntity).delete();
	}

	@DisplayName("유저는 댓글을 삭제하는 것에 실패한다.")
	@Nested
	class delete_comment_fail_case {
		@Test
		@DisplayName("없는 댓글을 요청했기 때문에 실패한다.")
		void not_exist_comment_delete_test() {
			// given
			Long commentId = 99999999L;
			Long userId = 1L;

			// when & then
			assertThrows(
					NullPointerException.class, () -> deleteCommentService.execute(commentId, userId));
		}

		@Test
		@DisplayName("본인이 작성하지 않은 댓글 삭제를 요청했기 때문에 실패한다.")
		void no_owner_delete_test() {
			// given
			Long commentId = 1L;
			Long userId = 1L;
			Long wrongUserId = 2L;
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
					NoOwnershipException.class, () -> deleteCommentService.execute(commentId, wrongUserId));
		}
	}
}
