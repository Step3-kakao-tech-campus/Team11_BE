package com.kakao.golajuma.comment.api;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
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
public class GetCommentListApiTest {

	@Autowired private MockMvc mvc;
	@Autowired private TokenProvider tokenProvider;

	private String jwtToken;

	@BeforeEach
	void setUp() {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("유저는 투표에 따른 댓글 리스트를 확인할 수 있다.")
	@Test
	public void get_comment_list_success_test() throws Exception {
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.get("/votes/1/comments")
								.header("Authorization", "Bearer " + jwtToken)
								.contentType(MediaType.APPLICATION_JSON));

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.comments[0].isOwner").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.comments[0].username").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.comments[0].content").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath());
	}
}
