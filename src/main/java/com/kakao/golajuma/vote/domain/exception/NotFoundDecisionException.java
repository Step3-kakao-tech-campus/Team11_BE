package com.kakao.golajuma.vote.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundDecisionException extends BusinessException {

	public NotFoundDecisionException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
