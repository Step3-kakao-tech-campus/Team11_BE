package com.kakao.golajuma.vote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.util.ArrayList;
import java.util.List;
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
public class VoteCreateControllerTest {

	@Autowired private ObjectMapper om;
	@Autowired private MockMvc mvc;

	@DisplayName("투표 생성 정상 작동")
	@Test
	public void createVoteTest() throws Exception {
		List<CreateVoteRequest.OptionDTO> options = new ArrayList<>();
		CreateVoteRequest.OptionDTO option1 = new CreateVoteRequest.OptionDTO("가라", "image1");
		CreateVoteRequest.OptionDTO option2 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", "60", options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes").content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(status().isOk());

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}

	@DisplayName("투표 생성 시 제목 입력 안했을 경우")
	@Test
	public void createVoteTest_error1() throws Exception {
		List<CreateVoteRequest.OptionDTO> options = new ArrayList<>();
		CreateVoteRequest.OptionDTO option1 = new CreateVoteRequest.OptionDTO("가라", "image1");
		CreateVoteRequest.OptionDTO option2 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest(null, "total", "...", "60", options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes").content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(status().is4xxClientError());

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}

	@DisplayName("투표 생성 시 옵션명이 없는 경우")
	@Test
	public void createVoteTest_error2() throws Exception {
		List<CreateVoteRequest.OptionDTO> options = new ArrayList<>();
		CreateVoteRequest.OptionDTO option1 = new CreateVoteRequest.OptionDTO(null, "image1");
		CreateVoteRequest.OptionDTO option2 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		options.add(option1);
		options.add(option2);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", "60", options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes").content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(status().is4xxClientError());

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}

	@DisplayName("투표 생성 시 옵션이 6개 초과인 경우")
	@Test
	public void createVoteTest_error3() throws Exception {
		List<CreateVoteRequest.OptionDTO> options = new ArrayList<>();
		CreateVoteRequest.OptionDTO option1 = new CreateVoteRequest.OptionDTO("가라", "image1");
		CreateVoteRequest.OptionDTO option2 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		CreateVoteRequest.OptionDTO option3 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		CreateVoteRequest.OptionDTO option4 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		CreateVoteRequest.OptionDTO option5 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		CreateVoteRequest.OptionDTO option6 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		CreateVoteRequest.OptionDTO option7 = new CreateVoteRequest.OptionDTO("가지마라", "image2");
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		options.add(option6);
		options.add(option7);

		CreateVoteRequest request = new CreateVoteRequest("군대 가야할까요?", "total", "...", "60", options);

		String requestBody = om.writeValueAsString(request);
		System.out.println("테스트 : " + requestBody);

		// when
		ResultActions resultActions =
				mvc.perform(
						post("/votes").content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(status().is4xxClientError());

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}
}
