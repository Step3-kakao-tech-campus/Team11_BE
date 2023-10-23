package com.kakao.golajuma.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
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
public class UpdateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;
	@Autowired private CommentRepository commentRepository;

	private String jwtToken;

	@DisplayName("comment-update-success-test")
	@Test
	public void updateTest() throws Exception {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
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
}
