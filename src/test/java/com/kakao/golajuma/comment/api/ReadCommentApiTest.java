package com.kakao.golajuma.comment.api;

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
public class ReadCommentApiTest {

	@Autowired private MockMvc mvc;

	@DisplayName("comment-read-success-case")
	@Test
	public void readTest() throws Exception {
		// given

		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.post("/votes/1/comments")
								.contentType(MediaType.APPLICATION_JSON));

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("테스트 : " + responseBody);
	}
}
