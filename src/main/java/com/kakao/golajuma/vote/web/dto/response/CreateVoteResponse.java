package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateVoteResponse implements AbstractResponseDto {
	private long id;

	public CreateVoteResponse(long id) {
		this.id = id;
	}
}
