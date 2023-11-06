package com.kakao.golajuma.vote.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@ExtendWith(MockitoExtension.class)
public class GetHotVoteListServiceTest {
	@InjectMocks private GetHotVoteListService getHotVoteListService;

	@Mock private GetVoteService getVoteService;

	@Nested
	@DisplayName("핫게시판 요청 성공 시 이전 1시간 동안 투표 수가 가장 많은 순서로 리스트를 반환한다.")
	class success_get_hot_vote_service {
		@Test
		@DisplayName("현재 시각으로부터 1시간 전 동안에 해당하는 시간을 계산한다.")
		void success_find_last_time() {
			// given
			int beforeHour = 1;
			int interval = 1;
			// when
			List<LocalDateTime> result = getHotVoteListService.findLastTime(beforeHour, interval);
			// then
			assertThat(result.get(0))
					.isEqualTo(LocalDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0));
			assertThat(result.get(1))
					.isEqualTo(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
		}

		@Test
		@DisplayName("가져온 엔티티 리스트를 dto리스트로 변환한다.")
		void success_vote_entity_to_dto() {
			// given
			VoteEntity voteEntity =
					VoteEntity.builder().id(1L).voteTitle("title").voteContent("content").build();

			List<VoteEntity> voteEntityList = new ArrayList<>();
			voteEntityList.add(voteEntity);

			Slice<VoteEntity> voteEntitySlice =
					new SliceImpl<>(voteEntityList, Pageable.unpaged(), false);

			when(getVoteService.getVote(any(VoteEntity.class), anyLong()))
					.thenReturn(new VoteDto(voteEntity, "username", "continue", false, false, "total"));

			// when
			List<VoteDto> result = getHotVoteListService.voteDtoConverter(voteEntitySlice, 1L);

			// then
			assertThat(result.get(0).getClass()).isEqualTo(VoteDto.class);
			assertThat(result.get(0).getId()).isEqualTo(1L);
			assertThat(result.get(0).getTitle()).isEqualTo("title");
			assertThat(result.get(0).getContent()).isEqualTo("content");
			assertThat(result.get(0).getActive()).isEqualTo("continue");
		}
	}
}
