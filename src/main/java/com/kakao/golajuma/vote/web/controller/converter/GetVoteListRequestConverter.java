package com.kakao.golajuma.vote.web.controller.converter;

import com.kakao.golajuma.vote.domain.service.Sort;
import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetVoteListRequestConverter {

	public Sort toSort(String sort) {
		return Sort.findSort(sort);
	}

	public Active toActive(String active) {
		return Active.findActive(active);
	}

	public Category toCategory(String category) {
		return Category.findCategory(category);
	}
}
