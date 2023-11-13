package com.kakao.golajuma.vote.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class SearchVoteListControllerTest {

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

	@DisplayName("투표 리스트 검색 정상 응답")
	@Test
	public void searchVoteList_test() throws Exception {

		String sort = "current";
		String category = "total";
		String keyword = "군대";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes/search")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", "0")
								.param("keyword", keyword)
								.param("sort", sort)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 검색 정상 응답 - 인기순, 전체 카테고리")
	@Test
	public void searchVoteList_test_popular() throws Exception {

		String sort = "popular";
		String category = "total";
		String keyword = "군대";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes/search")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", "0")
								.param("keyword", keyword)
								.param("sort", sort)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 검색 정상 응답 - 최신순, 특정 카테고리")
	@Test
	public void searchVoteList_test_specificCategory() throws Exception {

		String sort = "current";
		String category = "food";
		String keyword = "군대";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes/search")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", "0")
								.param("keyword", keyword)
								.param("sort", sort)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 검색 정상 응답 - 인기순, 특정 카테고리")
	@Test
	public void searchVoteList_test_popularAndspecificCategory() throws Exception {

		String sort = "popular";
		String category = "food";
		String keyword = "군대";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes/search")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", "0")
								.param("keyword", keyword)
								.param("sort", sort)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}
}
