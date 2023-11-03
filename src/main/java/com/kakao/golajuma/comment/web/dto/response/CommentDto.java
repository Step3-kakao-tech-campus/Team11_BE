package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto implements AbstractResponseDto {
	private Long id;
	private Boolean isOwner;
	private String username;
	private String content;
	private LocalDateTime createTime;

	public CommentDto(CommentEntity entity, Boolean isOwner, String username) {
		this.id = entity.getId();
		this.isOwner = isOwner;
		this.username = username;
		this.content = entity.getContent();
		this.createTime = entity.getUpdatedDate();
	}
}
