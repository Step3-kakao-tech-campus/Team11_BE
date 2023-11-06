package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;

public class UpdateCommentResponse extends CommentDto {
	public UpdateCommentResponse(CommentEntity commentEntity, Boolean isOwner, String username) {
		super(commentEntity, isOwner, username);
	}
}
