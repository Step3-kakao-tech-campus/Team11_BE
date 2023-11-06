package com.kakao.golajuma.auth.api;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReadUserProfileControllerTest {

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;

	@Autowired UserRepository userRepository;

	private String jwtToken;

	@BeforeEach
	public void setup() throws Exception {
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("유저 프로필 불러오기 성공")
	@Test
	public void success_read_profile_test() throws Exception {
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.get("/users/profile")
								.header("Authorization", "Bearer " + jwtToken));
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.nickName").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.image").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.participateVoteCount").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.createVoteCount").hasJsonPath());
	}
}
