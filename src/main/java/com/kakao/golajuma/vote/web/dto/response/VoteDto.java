package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.common.marker.AbstractResponseDto;
import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class VoteDto implements AbstractResponseDto {
	private long id;
	private String username;
	private Boolean isOwner;
	private long totalCount;
	private LocalDateTime createdDate;
	private LocalDateTime endDate;
	private String active;
	private boolean participate;
	private String category;
	private String title;
	private String content;
	private List<? super OptionDto> options = new ArrayList<>();

	public VoteDto(
			VoteEntity vote,
			String username,
			String active,
			boolean isOwner,
			boolean participate,
			String category) {
		this.id = vote.getId();
		this.username = username;
		this.isOwner = isOwner;
		this.totalCount = vote.getVoteTotalCount();
		this.createdDate = vote.getCreatedDate();
		this.endDate = vote.getVoteEndDate();
		this.active = active;
		this.participate = participate;
		this.category = category;
		this.title = vote.getVoteTitle();
		this.content = vote.getVoteContent();
	}

	public static VoteDto makeDto(
			VoteEntity vote,
			UserEntity username,
			Active active,
			boolean isOwner,
			boolean participate,
			String category,
			List<? super OptionDto> options) {
		VoteDto voteDto =
				new VoteDto(
						vote, username.getNickname(), active.getActive(), isOwner, participate, category);

		voteDto.addOption(options);

		return voteDto;
	}

	public void addOption(List<? super OptionDto> options) {
		for (Object option : options) {
			if (option instanceof CountOptionDto) this.options.add((CountOptionDto) option);
			else this.options.add((OptionDto) option);
		}
	}
}
