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
	/**
	 * 해당 클래스의 객체 생성자
	 *
	 * @implSpec 이 클래스의 하위 클래스들은 각각의 요소를 이 클래스와 같은 요소들로 생성한다.
	 */
	public CommentDto(CommentEntity entity, Boolean isOwner, String username) {
		this.id = entity.getId();
		this.isOwner = isOwner;
		this.username = username;
		this.content = entity.getContent();
		this.createTime = entity.getUpdatedDate();
	}
}
