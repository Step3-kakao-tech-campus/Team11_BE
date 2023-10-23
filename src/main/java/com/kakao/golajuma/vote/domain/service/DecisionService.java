package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundException;
import com.kakao.golajuma.vote.domain.converter.DecisionEntityConverter;
import com.kakao.golajuma.vote.domain.exception.CompletionVoteException;
import com.kakao.golajuma.vote.domain.exception.ExistsDecisionException;
import com.kakao.golajuma.vote.domain.exception.NotFoundDecisionException;
import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.converter.DecisionResponseConverter;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecisionService {
	private final DecisionEntityConverter entityConverter;
	private final DecisionRepository decisionRepository;
	private final OptionRepository optionRepository;
	private final VoteRepository voteRepository;
	private final DecisionResponseConverter responseConverter;

	/**
	 * 옵션에 대한 투표를 한다.
	 *
	 * @param userId 유저 식별자
	 * @param optionId 옵션 식별자
	 * @throws ExistsDecisionException 해당 옵션에 대해 이미 투표를 진행했을 시 에러가 발생한다.
	 * @throws NotFoundDecisionException 존재하지 않는 vote에 투표 했을 시 에러가 발생한다.
	 * @throws CompletionVoteException 종료된 투표에 대해 투표를 변경했을 시 에러가 발생한다.
	 */
	public DecisionResponse createDecision(final Long userId, final Long optionId) {
		boolean isExistDecision = decisionRepository.existsByUserIdAndOptionId(userId, optionId);
		if (isExistDecision) {
			throw new ExistsDecisionException("이미 해당 옵션에 대해 투표를 했습니다.");
		}
		OptionEntity optionEntity =
				optionRepository
						.findById(optionId)
						.orElseThrow(() -> new NotFoundException("존재하지 않는 옵션 입니다."));

		VoteEntity voteEntity =
				voteRepository
						.findVoteByOption(optionId)
						.orElseThrow(() -> new NotFoundException("존재하지 않는 투표 입니다."));

		if (voteEntity.isComplete()) {
			throw new CompletionVoteException("종료된 투표에 대해선 참여할 수 없습니다.");
		}

		DecisionEntity entity = entityConverter.from(userId, optionId);
		decisionRepository.save(entity);

		increaseCount(voteEntity, optionEntity);

		return toResponse(voteEntity, optionId);
	}

	/**
	 * 옵션에 대한 투표를 취소한다.
	 *
	 * @param userId 유저 식별자
	 * @param optionId 옵션 식별자
	 * @throws NotFoundDecisionException 해당 옵션에 투표를 한 기록이 없을 시 에러가 발생한다.
	 * @throws NotFoundDecisionException 존재하지 않는 vote에 투표 했을 시 에러가 발생한다.
	 * @throws CompletionVoteException 종료된 투표에 대해 투표를 변경했을 시 에러가 발생한다.
	 */
	public DecisionResponse deleteDecision(final Long userId, final Long optionId) {
		DecisionEntity entity =
				decisionRepository
						.findByUserIdAndOptionId(userId, optionId)
						.orElseThrow(() -> new NotFoundDecisionException("해당 옵션에 투표를 한 기록이 없습니다"));

		OptionEntity optionEntity =
				optionRepository
						.findById(optionId)
						.orElseThrow(() -> new NotFoundException("존재하지 않는 옵션 입니다."));

		VoteEntity voteEntity =
				voteRepository
						.findVoteByOption(optionId)
						.orElseThrow(() -> new NotFoundException("존재하지 않는 투표 입니다."));

		if (voteEntity.isComplete()) {
			throw new CompletionVoteException("종료된 투표에 대해선 참여 여부를 변경할 수 없습니다.");
		}

		decisionRepository.delete(entity);

		decreaseCount(voteEntity, optionEntity);

		return toResponse(voteEntity, optionId);
	}

	private void increaseCount(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.updateCount();
		optionEntity.updateCount();
	}

	private void decreaseCount(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.decreaseCount();
		optionEntity.decreaseCount();
	}

	private DecisionResponse toResponse(final VoteEntity voteEntity, final Long selectOptionId) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(selectOptionId, optionsByVote, voteEntity.getVoteTotalCount());
	}
}
