package com.kakao.golajuma.comment.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
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
public class CreateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;

	private String jwtToken;

	@BeforeEach
	void setUp() {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("유저는 댓글을 작성하는데 성공한다.")
	@Test
	public void create_comment__success_test() throws Exception {
		// given
		CreateCommentRequest requestDto = CreateCommentRequest.builder().content("메롱이다.").build();
		String requestBody = om.writeValueAsString(requestDto);

		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.post("/votes/1/comments")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.isOwner").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.username").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.content").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("유저는 새로운 댓글을 저장하는데 실패한다.")
	@Nested
	class create_comment_fail_case {
		@DisplayName("유저는 아무런 댓글을 작성하지 않고 저장해서 실패한다.")
		@Test
		void create_empty_comment_fail_test() throws Exception {
			// given
			CreateCommentRequest requestDto = CreateCommentRequest.builder().content("").build();
			String requestBody = om.writeValueAsString(requestDto);

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.post("/votes/1/comments")
									.header("Authorization", "Bearer " + jwtToken)
									.content(requestBody)
									.contentType(MediaType.APPLICATION_JSON));

			resultActions.andExpect(status().isBadRequest());
		}

		@DisplayName("비로그인 유저가 댓글을 작성하려 하기 때문에 실패한다.")
		@Test
		void not_exist_user_create_comment_fail_test() throws Exception {
			// given
			CreateCommentRequest requestDto = CreateCommentRequest.builder().content("").build();
			String requestBody = om.writeValueAsString(requestDto);

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.post("/votes/1/comments")
									.content(requestBody)
									.contentType(MediaType.APPLICATION_JSON));

			resultActions.andExpect(status().isNotFound());
		}
	}
}
