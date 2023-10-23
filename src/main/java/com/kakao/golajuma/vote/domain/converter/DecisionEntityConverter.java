package com.kakao.golajuma.vote.domain.converter;

import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import org.springframework.stereotype.Component;

@Component
public class DecisionEntityConverter {
	public DecisionEntity from(final Long userId, final Long optionId) {
		return DecisionEntity.builder().userId(userId).optionId(optionId).build();
	}
}
