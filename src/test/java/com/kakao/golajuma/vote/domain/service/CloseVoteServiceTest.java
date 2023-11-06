package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CloseVoteServiceTest {
	@InjectMocks private CloseVoteService closeVoteService;

	@DisplayName("존재 하지 않는 투표에 대한 마감 실패")
	@Test
	public void closeVoteTest() {
		// given
		Long userId = 1L;
		Long voteId = -1L;

		// when & then
		assertThrows(NullPointerException.class, () -> closeVoteService.closeVote(voteId, userId));
	}
}
