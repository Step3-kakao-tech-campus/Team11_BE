package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.auth.web.supplier.NicknameSupplier;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ValidNicknameRequest implements AbstractRequestDto, NicknameSupplier {
	String nickname;
}
