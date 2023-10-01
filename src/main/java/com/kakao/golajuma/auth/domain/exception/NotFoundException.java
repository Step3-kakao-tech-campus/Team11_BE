package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {

	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
