package com.kakao.golajuma.vote.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GetVoteDetailControllerTest {

	@Autowired private ObjectMapper om;
	@Autowired private MockMvc mvc;

	@Autowired private UserRepository userRepository;
	@Autowired private TokenProvider tokenProvider;
	private String jwtToken;

	@BeforeEach
	public void setup() throws Exception {
		jwtToken = tokenProvider.createAccessToken(1L);
		UserEntity user =
				UserEntity.builder()
						.id(1L)
						.nickname("test")
						.email("test@gmail.com")
						.password("1234")
						.build();
		userRepository.save(user);
	}

	@DisplayName("투표 상세 조회 정상 요청")
	@Test
	public void getVoteDetail_test() throws Exception {
		// given
		Long voteId = 1L;
		// when
		ResultActions resultActions =
				mvc.perform(get("/votes/" + voteId).header("Authorization", "Bearer " + jwtToken));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.vote").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.id").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.username").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.isOwner").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.totalCount").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.createdDate").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.endDate").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.active").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.participate").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.title").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.content").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options").isArray())
				.andExpect(jsonPath("$.data.vote.options[0].id").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options[0].optionName").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options[0].image").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options[0].choice").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options[0].optionCount").hasJsonPath())
				.andExpect(jsonPath("$.data.vote.options[0].optionRatio").hasJsonPath())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("존재하지 않는 투표 조회하는 경우")
	@Test
	public void getVoteDetail_error1() throws Exception {
		// given
		Long voteId = -1L;
		// when
		ResultActions resultActions =
				mvc.perform(get("/votes/" + voteId).header("Authorization", "Bearer " + jwtToken));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions.andExpect(status().is4xxClientError());
	}
}
