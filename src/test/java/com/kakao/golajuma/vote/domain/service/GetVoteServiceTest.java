package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetVoteServiceTest {
	@Mock OptionRepository optionRepository;
	@Mock UserRepository userRepository;
	@Mock DecisionRepository decisionRepository;
	@InjectMocks private GetVoteService getVoteService;

	@DisplayName("투표 조회 dto 변환 정상 응답")
	@Test
	public void getVoteTest() {
		// OptionEntity 목록 생성
		List<OptionEntity> options = new ArrayList<>();
		options.add(OptionEntity.builder().build());
		options.add(OptionEntity.builder().build());

		// UserEntity 생성
		UserEntity userEntity = UserEntity.builder().id(1L).build();

		// VoteEntity 생성
		VoteEntity voteEntity =
				VoteEntity.builder()
						.id(1L)
						.userId(1L)
						.voteEndDate(LocalDateTime.now())
						.category(Category.TOTAL)
						.build();

		when(optionRepository.findAllByVoteId(any())).thenReturn(options);
		when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
		when(decisionRepository.existsByUserIdAndOptionId(any(), any())).thenReturn(true);

		// 테스트할 메서드를 호출합니다.
		VoteDto result = getVoteService.getVote(voteEntity, 1L);

		// 결과를 검증합니다.
		assertEquals(2, result.getOptions().size()); // 예상되는 옵션 개수에 따라 수정
		assertEquals(true, result.getIsOwner());
		assertEquals("total", result.getCategory());
	}
}
