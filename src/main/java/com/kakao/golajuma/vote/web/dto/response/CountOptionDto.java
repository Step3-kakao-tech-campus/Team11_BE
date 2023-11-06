package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.util.ImageUploader;
import com.kakao.golajuma.vote.util.OptionDivideUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CountOptionDto extends OptionDto {

	private boolean choice;
	private long optionCount;
	private double optionRatio;

	public CountOptionDto(
			long id, String name, String image, boolean choice, long count, double ratio) {
		super(id, name, image);
		this.choice = choice;
		this.optionCount = count;
		this.optionRatio = ratio;
	}

	public static CountOptionDto makeCountOptionDto(
			OptionEntity option, boolean choice, int totalCount) {
		String image = ImageUploader.getImage(option.getOptionImage());
		if (totalCount == 0) {
			totalCount = 1;
		}
		double ratio = OptionDivideUtil.getRatio(option.getOptionCount(), totalCount);
		return CountOptionDto.builder()
				.id(option.getId())
				.optionName(option.getOptionName())
				.image(image)
				.choice(choice)
				.optionCount(option.getOptionCount())
				.optionRatio(ratio)
				.build();
	}
}
