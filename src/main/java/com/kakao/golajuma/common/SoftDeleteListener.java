package com.kakao.golajuma.common;

import javax.persistence.PreRemove;

public class SoftDeleteListener {

	@PreRemove
	private void preRemove(BaseEntity entity) {
		entity.delete();
	}
}
