package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.CloseException;
import com.kakao.golajuma.vote.domain.exception.NullException;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CloseVoteService {
	private final VoteRepository voteRepository;

	public void execute(Long voteId, Long userId) {
		VoteEntity voteEntity =
				voteRepository.findById(voteId).orElseThrow(() -> new NullException("해당 투표가 존재하지 않습니다."));
		// 작성자가 아닌 경우 예외
		if (!voteEntity.isOwner(userId)) {
			throw new CloseException("투표 작성자가 아닙니다.");
		}
		// 이미 마감된 경우 예외
		if (!voteEntity.isOn()) {
			throw new CloseException("이미 완료된 투표입니다.");
		}
		voteEntity.close();
	}
}
