package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.SortException;
import java.util.Arrays;

public enum Sort {
	CURRENT("current"),
	POPULAR("popular");

	private final String sort;

	Sort(String sort) {
		this.sort = sort;
	}

	public String getSort() {
		return this.sort;
	}

	public static Sort findSort(String sort) {
		return Arrays.stream(Sort.values())
				.filter(Category -> Category.getSort().equals(sort))
				.findAny()
				.orElseThrow(() -> new SortException("잘못된 정렬 방식입니다."));
	}

	public static boolean isCurrentRequest(String sort) {
		return findSort(sort) == Sort.CURRENT;
	}
}
