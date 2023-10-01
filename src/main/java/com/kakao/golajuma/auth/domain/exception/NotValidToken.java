package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotValidToken extends BusinessException {

	public NotValidToken(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
