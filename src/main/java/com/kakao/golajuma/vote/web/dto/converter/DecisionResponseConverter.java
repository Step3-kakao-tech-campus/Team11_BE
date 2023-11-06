package com.kakao.golajuma.vote.web.dto.converter;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DecisionResponseConverter {
	private final OptionResultConverter optionResultConverter;

	public DecisionResponse from(Long selectedId, List<OptionEntity> optionsByVote, int totalCount) {
		return DecisionResponse.builder()
				.choice(selectedId)
				.result(optionResultConverter.from(optionsByVote, totalCount))
				.build();
	}
}
