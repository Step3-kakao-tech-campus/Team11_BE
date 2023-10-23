package com.kakao.golajuma.comment.api;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
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
public class DeleteCommentApiTest {
	@Autowired private MockMvc mvc;
	@Autowired private TokenProvider tokenProvider;
	@Autowired private CommentRepository commentRepository;
	private String jwtToken;

	@DisplayName("comment-delete-success-case")
	@Test
	public void deleteTest() throws Exception {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
		CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
		Long voteId = commentEntity.getVoteId();
		Long commentId = commentEntity.getId();
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.delete("/votes/" + voteId + "/comments/" + commentId)
								.header("Authorization", "Bearer " + jwtToken)
								.contentType(MediaType.APPLICATION_JSON));

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath());
	}
}
