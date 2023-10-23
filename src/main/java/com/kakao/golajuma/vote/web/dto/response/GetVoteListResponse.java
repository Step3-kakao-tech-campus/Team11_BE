package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@Getter
public class GetVoteListResponse implements AbstractResponseDto {

	@Getter
	public static class MainAndFinishPage {
		List<VoteDto> votes;
		Boolean isLast;

		public MainAndFinishPage(List<VoteDto> votes, boolean isLast) {
			this.votes = votes;
			this.isLast = isLast;
		}
	}

	@Getter
	public static class MyPage {
		List<VoteListDto> votes = new ArrayList<>();

		@Builder
		@Getter
		public static class VoteListDto {
			long id;
			String active;
			String title;
		}

		public void toDto(List<VoteEntity> votes) {
			for (VoteEntity vote : votes) {
				this.votes.add(voteToDto(vote));
			}
		}

		public VoteListDto voteToDto(VoteEntity vote) {
			String active = vote.checkActive().getActive();
			return VoteListDto.builder()
					.id(vote.getId())
					.active(active)
					.title(vote.getVoteTitle())
					.build();
		}
	}
}
