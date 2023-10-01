package com.kakao.golajuma.common.support.converter;

import com.kakao.golajuma.common.BaseEntity;
import com.kakao.golajuma.common.marker.AbstractDto;

public interface AbstractEntityConverter<T extends BaseEntity, R extends AbstractDto> {
	T toEntity(R t);
}
