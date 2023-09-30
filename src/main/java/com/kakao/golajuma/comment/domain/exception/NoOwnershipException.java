package com.kakao.golajuma.comment.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NoOwnershipException extends BusinessException {
	public NoOwnershipException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}
}
