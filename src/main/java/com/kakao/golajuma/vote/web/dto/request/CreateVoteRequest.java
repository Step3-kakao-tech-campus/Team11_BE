package com.kakao.golajuma.vote.web.dto.request;

import com.kakao.golajuma.common.marker.AbstractRequestDto;
import com.kakao.golajuma.vote.util.OptionCheck;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class CreateVoteRequest implements AbstractRequestDto {
	@NotBlank(message = "투표 제목은 필수입니다.")
	@Size(max = 256, message = "투표 제목은 256자를 넘을 수 없습니다.")
	private String title;

	@Size(max = 1000, message = "투표 내용은 1000자를 넘을 수 없습니다.")
	private String content;

	@OptionCheck List<OptionDto> options;

	private String category;
	private int timeLimit; // 시간 제한 받아서 연산해야함

	public CreateVoteRequest(
			String voteTitle,
			String category,
			String voteContent,
			int timeLimit,
			List<OptionDto> options) {
		this.title = voteTitle;
		this.category = category;
		this.content = voteContent;
		this.timeLimit = timeLimit;
		this.options = options;
	}

	@Getter
	public static class OptionDto {
		@Size(max = 200, message = "옵션명은 200자를 넘을 수 없습니다.")
		private String name;

		private String image;

		public OptionDto(String name) {
			this.name = name;
			this.image = null;
		}

		public OptionDto(String name, String image) {
			this.name = name;
			this.image = image;
		}

		public String getImage() {
			return this.image;
		}
	}
}
