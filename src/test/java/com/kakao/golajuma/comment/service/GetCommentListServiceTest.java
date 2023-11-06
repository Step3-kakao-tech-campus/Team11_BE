package com.kakao.golajuma.comment.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.service.GetCommentListService;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.CommentDto;
import com.kakao.golajuma.comment.web.dto.response.GetCommentListResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCommentListServiceTest {
	@InjectMocks private GetCommentListService getCommentListService;

	@Mock private CommentRepository commentRepository;

	@Mock private UserRepository userRepository;

	@Test
	@DisplayName("유저가 댓글 리스트를 호출하는데 성공한다.")
	void get_comment_list_success_test() {
		// given
		Long userId = 1L;
		Long voteId = 1L;

		UserEntity userEntity = UserEntity.builder().id(1L).nickname("tester").build();

		CommentEntity commentEntity1 =
				CommentEntity.builder().voteId(voteId).userId(userId).content("content1").build();
		CommentEntity commentEntity2 =
				CommentEntity.builder().voteId(voteId).userId(userId).content("content2").build();

		List<CommentEntity> commentEntityList =
				Stream.of(commentEntity1, commentEntity2).collect(Collectors.toList());

		when(commentRepository.findByVoteId(voteId)).thenReturn(commentEntityList);
		when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
		// when
		GetCommentListResponse response = getCommentListService.execute(voteId, userId);
		// then
		assertThat(response.getComments().get(0).getClass()).isEqualTo(CommentDto.class);

		assertThat(response.getComments().get(0).getContent()).isEqualTo("content1");
		assertThat(response.getComments().get(1).getContent()).isEqualTo("content2");

		assertThat(response.getCommentCount()).isEqualTo(2);
	}
}
