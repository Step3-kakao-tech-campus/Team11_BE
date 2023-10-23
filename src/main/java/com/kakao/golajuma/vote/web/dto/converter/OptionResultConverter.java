package com.kakao.golajuma.vote.web.dto.converter;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.web.dto.response.OptionResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptionResultConverter {

	public List<OptionResult> from(List<OptionEntity> optionsByVote, Long totalCount) {
		return optionsByVote.stream()
				.map(
						entity ->
								OptionResult.builder()
										.id(entity.getId())
										.optionCount(entity.getOptionCount())
										.optionRatio(optionRatio(entity.getOptionCount(), totalCount))
										.build())
				.collect(Collectors.toList());
	}

	private Long optionRatio(Long optionCount, Long totalCount) {
		return optionCount / totalCount;
	}
}
