package com.kakao.golajuma.vote.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ImageException extends BusinessException {
	public ImageException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
