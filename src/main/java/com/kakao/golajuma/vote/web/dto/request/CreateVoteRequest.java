package com.kakao.golajuma.vote.web.dto.request;

import com.kakao.golajuma.common.marker.AbstractRequestDto;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class CreateVoteRequest implements AbstractRequestDto {

	private String title;
	private String content;
	List<OptionDTO> options;
	private String category;
	private String timeLimit; // 시간 제한 받아서 연산해야함

	public CreateVoteRequest(
			String voteTitle,
			String category,
			String voteContent,
			String timeLimit,
			List<OptionDTO> options) {
		this.title = voteTitle;
		this.category = category;
		this.content = voteContent;
		this.timeLimit = timeLimit;
		this.options = options;
	}

	public VoteEntity toEntity() {
		VoteEntity vote =
				VoteEntity.builder()
						.userId(1)
						//                .category(Category.valueOf(category))
						.category(Category.findCategory(category).get())
						//                .category(Category.TOTAL)
						.voteTitle(title)
						.voteContent(content)
						.voteType("null")
						.voteEndDate(LocalDateTime.now().plusMinutes(Integer.parseInt(timeLimit)))
						.build();
		System.out.println(vote);
		return vote;
	}

	@Getter
	@Setter
	public static class OptionDTO {
		private String name;
		private String image;

		public OptionDTO(String name, String image) {
			this.name = name;
			this.image = image;
		}

		public OptionEntity toEntity(long voteId) {
			return OptionEntity.builder().voteId(voteId).optionName(name).optionImage(image).build();
		}
	}
}
