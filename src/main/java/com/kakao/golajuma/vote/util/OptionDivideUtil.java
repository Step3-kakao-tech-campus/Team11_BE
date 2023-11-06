package com.kakao.golajuma.vote.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OptionDivideUtil {

	private static final int NOT_DIVIDE = 0;
	private static final double ZERO = 0;

	public static double getRatio(int optionCount, int totalCount) {
		if (totalCount == NOT_DIVIDE) {
			return ZERO;
		}

		double option = (double) optionCount;
		double total = (double) totalCount;

		return Math.round(((option / total * 100) * 10) / 10);
	}
}
