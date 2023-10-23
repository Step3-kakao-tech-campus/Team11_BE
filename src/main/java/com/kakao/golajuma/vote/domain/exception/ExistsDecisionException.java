package com.kakao.golajuma.vote.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExistsDecisionException extends BusinessException {

	public ExistsDecisionException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
