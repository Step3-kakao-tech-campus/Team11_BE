package com.kakao.golajuma.auth.persistence.entity;

import java.util.Arrays;
import java.util.Optional;

public enum LoginType {
	SERVICE("service");

	private final String typeName;

	LoginType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public Optional<LoginType> findLoginType(String type) {
		return Arrays.stream(LoginType.values())
				.filter(loginType -> loginType.getTypeName().equals(type))
				.findFirst();
	}
}
