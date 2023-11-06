package com.kakao.golajuma.auth.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserProfileResponse {

	private String nickName;

	private String email;

	private String image;

	private int createVoteCount;

	private int participateVoteCount;
}
