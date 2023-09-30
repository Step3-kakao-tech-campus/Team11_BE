package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadCommentListResponse implements AbstractResponseDto {
	private List<ReadCommentDto> readCommentDtoList;
}
