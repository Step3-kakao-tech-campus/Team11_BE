package com.kakao.golajuma.vote.infra.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
	TOTAL("total"),
	CLOTHES("clothes"),
	FOOD("food");

	private final String category;

	Category(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

	public static Optional<Category> findCategory(String category) {
		return Arrays.stream(Category.values())
				.filter(Category -> Category.getCategory().equals(category))
				.findAny();
	}
}
