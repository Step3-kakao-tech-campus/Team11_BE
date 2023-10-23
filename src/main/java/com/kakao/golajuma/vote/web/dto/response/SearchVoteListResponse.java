package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class SearchVoteListResponse implements AbstractResponseDto {
	List<VoteDto> votes;
	Boolean isLast;

	public SearchVoteListResponse(List<VoteDto> votes, boolean isLast) {
		this.votes = votes;
		this.isLast = isLast;
	}
}
