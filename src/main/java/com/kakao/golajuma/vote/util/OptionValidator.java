package com.kakao.golajuma.vote.util;

import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValidator
		implements ConstraintValidator<OptionCheck, List<CreateVoteRequest.OptionDto>> {

	private static final int MIN_NUM = 2;
	private static final int MAX_NUM = 6;

	@Override
	public boolean isValid(
			List<CreateVoteRequest.OptionDto> value, ConstraintValidatorContext context) {
		Objects.requireNonNull(value);
		if (isNullOptionName(value)) {
			addConstraintViolation(context, "옵션명은 필수입니다.");
			return false;
		}
		if (underMinNum(value) || overMaxNum(value)) {
			addConstraintViolation(context, "옵션 개수는 2개 이상 6개 이하여야 합니다.");
			return false;
		}

		return true;
	}

	public boolean isNullOptionName(List<CreateVoteRequest.OptionDto> value) {
		return value.stream().anyMatch(this::isNull);
	}

	public boolean isNull(CreateVoteRequest.OptionDto value) {
		return value.getName() == null;
	}

	public boolean underMinNum(List<CreateVoteRequest.OptionDto> value) {
		int size = value.size();
		return size < MIN_NUM;
	}

	private boolean overMaxNum(List<CreateVoteRequest.OptionDto> value) {
		int size = value.size();
		return size > MAX_NUM;
	}

	private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage) {
		// 기본 에러 메시지 비활성화
		context.disableDefaultConstraintViolation();
		// 새로운 에러 메시지 추가
		context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
	}
}
