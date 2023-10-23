package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CountOptionDto extends OptionDto {
	private boolean choice;
	private long optionCount;
	private int optionRatio;

	public CountOptionDto(long id, String name, String image, boolean choice, long count, int ratio) {
		super(id, name, image);
		this.choice = choice;
		this.optionCount = count;
		this.optionRatio = ratio;
	}

	public static CountOptionDto makeCountOptionDto(
			OptionEntity option, boolean choice, long totalCount) {
		if (totalCount == 0) totalCount = 1;
		int ratio = Math.round(option.getOptionCount() * 100 / totalCount);
		return CountOptionDto.builder()
				.id(option.getId())
				.optionName(option.getOptionName())
				.image(option.getOptionImage())
				.choice(choice)
				.optionCount(option.getOptionCount())
				.optionRatio(ratio)
				.build();
	}
}
