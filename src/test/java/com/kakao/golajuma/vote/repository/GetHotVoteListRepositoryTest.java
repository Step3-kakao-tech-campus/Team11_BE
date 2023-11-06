package com.kakao.golajuma.vote.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.HotVoteRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Import(ObjectMapper.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetHotVoteListRepositoryTest {

	@Autowired private HotVoteRepository hotVoteRepository;

	@Autowired VoteRepository voteRepository;

	@Autowired DecisionRepository decisionRepository;

	@Test
	@Transactional
	public void hotVote_findByTimeLimitAndDecisionCount_test() throws JsonProcessingException {
		// given
		// 1시간 전에 투표를 했음
		DecisionEntity decisionEntity =
				DecisionEntity.builder()
						.userId(1L)
						.optionId(1L)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.deleted(false)
						.build();
		decisionRepository.save(decisionEntity);
		// 1시간 전의 시간 데이터
		LocalDateTime startTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
		LocalDateTime endTime = startTime.plusHours(1);

		// when
		// 1시간 전 투표를 받았던 투표들을 가져옴.
		Slice<VoteEntity> voteEntitySlice =
				hotVoteRepository.findByTimeLimitAndDecisionCount(startTime, endTime, Pageable.unpaged());
		// then
		assertThat(voteEntitySlice.toList().get(0).getClass()).isEqualTo(VoteEntity.class);
	}
}
