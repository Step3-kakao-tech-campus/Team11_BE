package com.kakao.golajuma.comment.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UpdateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;
	@Autowired private CommentRepository commentRepository;

	private String jwtToken;

	@BeforeEach
	void setUp() {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("유저는 댓글을 업데이트 하는데 성공한다.")
	@Test
	public void updateTest() throws Exception {
		// given
		CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
		Long voteId = commentEntity.getVoteId();
		Long commentId = commentEntity.getId();
		UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("메롱이다.22").build();
		String requestBody = om.writeValueAsString(requestDto);

		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.put("/votes/" + voteId + "/comments/" + commentId)
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));

		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.isOwner").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.username").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.content").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("유저는 댓글을 업데이트 하는데 실패한다.")
	@Nested
	class update_comment_fail_case {
		@DisplayName("유저는 아무런 댓글을 작성하지 않고 업데이트해서 실패한다.")
		@Test
		void update_empty_comment_fail_test() throws Exception {
			// given
			CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
			Long voteId = commentEntity.getVoteId();
			Long commentId = commentEntity.getId();
			UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("").build();
			String requestBody = om.writeValueAsString(requestDto);

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.put("/votes/" + voteId + "/comments/" + commentId)
									.header("Authorization", "Bearer " + jwtToken)
									.content(requestBody)
									.contentType(MediaType.APPLICATION_JSON));

			resultActions.andExpect(status().isBadRequest());
		}

		@DisplayName("다른 유저의 댓글을 수정하기 때문에 실패한다.")
		@Test
		void not_owner_update_comment_fail_test() throws Exception {
			// given
			CommentEntity commentEntity = commentRepository.findByUserId(2L).stream().findFirst().get();
			Long voteId = commentEntity.getVoteId();
			Long commentId = commentEntity.getId();
			UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("fail").build();
			String requestBody = om.writeValueAsString(requestDto);

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.put("/votes/" + voteId + "/comments/" + commentId)
									.header("Authorization", "Bearer " + jwtToken)
									.content(requestBody)
									.contentType(MediaType.APPLICATION_JSON));

			resultActions.andExpect(status().isForbidden());
		}

		@DisplayName("없는 댓글을 수정하기 때문에 실패한다.")
		@Test
		void not_exist_comment_update_fail_test() throws Exception {
			// given
			CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
			Long voteId = commentEntity.getVoteId();
			Long commentId = 999999L;
			UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("fail").build();
			String requestBody = om.writeValueAsString(requestDto);

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.put("/votes/" + voteId + "/comments/" + commentId)
									.header("Authorization", "Bearer " + jwtToken)
									.content(requestBody)
									.contentType(MediaType.APPLICATION_JSON));

			resultActions.andExpect(status().isNotFound());
		}
	}
}
