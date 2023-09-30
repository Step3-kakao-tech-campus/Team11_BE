package com.kakao.golajuma.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CreateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@DisplayName("comment-create-success-case")
	@Test
	public void createTest() throws Exception {
		// given
		SaveCommentRequest requestDto = SaveCommentRequest.builder().content("메롱이다.").build();
		String requestBody = om.writeValueAsString(requestDto);
		System.out.println("테스트 : " + requestBody);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.post("/votes/1/comments")
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("테스트 : " + responseBody);
	}
}
