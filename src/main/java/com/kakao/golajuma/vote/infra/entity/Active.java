package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.vote.domain.exception.NullException;
import java.util.Arrays;

public enum Active {
	CONTINUE("continue"),
	COMPLETE("complete");

	private final String active;

	Active(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}

	public static Active findActive(String active) {
		return Arrays.stream(Active.values())
				.filter(Active -> Active.getActive().equals(active))
				.findAny()
				.orElseThrow(() -> new NullException("해당 active는 존재하지 않습니다."));
	}

	public static boolean isContinueRequest(Active active) {
		return active == Active.CONTINUE;
	}
}
