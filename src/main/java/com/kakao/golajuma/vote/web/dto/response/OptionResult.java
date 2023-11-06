package com.kakao.golajuma.vote.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OptionResult {
	private Long id;
	private int optionCount;
	private double optionRatio;
}
