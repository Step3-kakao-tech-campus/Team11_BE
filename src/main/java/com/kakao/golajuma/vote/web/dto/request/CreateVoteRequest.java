package com.kakao.golajuma.vote.web.dto.request;

import com.kakao.golajuma.common.marker.AbstractRequestDto;
import com.kakao.golajuma.vote.util.OptionCheck;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class CreateVoteRequest implements AbstractRequestDto {
	@NotBlank(message = "투표 제목은 필수입니다.")
	private String title;

	private String content;

	@OptionCheck List<CreateVoteRequest.OptionDTO> options;

	private String category;
	private int timeLimit; // 시간 제한 받아서 연산해야함

	public CreateVoteRequest(
			String voteTitle,
			String category,
			String voteContent,
			int timeLimit,
			List<OptionDTO> options) {
		this.title = voteTitle;
		this.category = category;
		this.content = voteContent;
		this.timeLimit = timeLimit;
		this.options = options;
	}

	@Getter
	public static class OptionDTO {
		private String name;
		private String image;

		public OptionDTO(String name, String image) {
			this.name = name;
			this.image = image;
		}
	}
}
