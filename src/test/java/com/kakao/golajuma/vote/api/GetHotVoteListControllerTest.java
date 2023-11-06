package com.kakao.golajuma.vote.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GetHotVoteListControllerTest {

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;

	@Autowired UserRepository userRepository;

	private String jwtToken;

	@BeforeEach
	public void setup() throws Exception {
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("인기 투표 불러오기 정상 작동")
	@Test
	public void GetHotVoteListTest() throws Exception {
		System.out.println(jwtToken);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.get("/votes/hot").header("Authorization", "Bearer " + jwtToken));

		resultActions.andExpect(status().is2xxSuccessful());

		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}
}
