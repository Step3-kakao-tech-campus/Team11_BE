package com.kakao.golajuma.vote.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.ImageDto;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CreateVoteControllerTest {

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

	@DisplayName("투표 생성 정상 작동 - 이미지 포함")
	@Test
	public void createVoteTestWithImage() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		ImageDto imageDto = new ImageDto();
		String base64 = imageDto.getImage();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("가라", base64);
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON_VALUE));

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.id").hasJsonPath())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 생성 정상 작동 - 이미지 미포함")
	@Test
	public void createVoteTestWithoutImage() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("가라");
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON_VALUE));

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.id").hasJsonPath())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 생성 시 제목 입력 안했을 경우")
	@Test
	public void createVoteTest_NoTitle() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("가라");
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest(null, "total", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes")
								.content(requestBody)
								.header("Authorization", "Bearer " + jwtToken)
								.contentType(MediaType.APPLICATION_JSON_VALUE));

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 생성 시 옵션명이 없는 경우")
	@Test
	public void createVoteTest_NoOptionName() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto(null);
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON_VALUE));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 생성 시 옵션이 6개 초과인 경우")
	@Test
	public void createVoteTest_ExceedOptionNum() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("가라");
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		CreateVoteRequest.OptionDto option3 = new CreateVoteRequest.OptionDto("가지마라");
		CreateVoteRequest.OptionDto option4 = new CreateVoteRequest.OptionDto("가지마라");
		CreateVoteRequest.OptionDto option5 = new CreateVoteRequest.OptionDto("가지마라");
		CreateVoteRequest.OptionDto option6 = new CreateVoteRequest.OptionDto("가지마라");
		CreateVoteRequest.OptionDto option7 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		options.add(option6);
		options.add(option7);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON_VALUE));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 생성 시 존재하지 않는 카테고리인 경우")
	@Test
	public void createVoteTest_CategoryException() throws Exception {
		List<CreateVoteRequest.OptionDto> options = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("가라");
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("가지마라");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request =
				new CreateVoteRequest("군대 가야할까요?", "no category", "...", 60, options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes").content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}
}
