package com.kakao.golajuma.comment.web.dto.request;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SaveCommentRequest implements AbstractRequestDto {

	@NotBlank // null, 빈 문자열, 스페이스만 포함한 문자열 불가
	@Size(min = 1, max = 255) // 최소 길이, 최대 길이 제한
	private String content;

	public CommentEntity toEntity(long voteId, long userId) {
		return CommentEntity.builder().voteId(voteId).userId(userId).content(this.content).build();
	}
}
