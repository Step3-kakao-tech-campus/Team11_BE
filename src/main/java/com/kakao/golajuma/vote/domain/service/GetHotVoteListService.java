package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.HotVoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetHotVoteListService {
	private final GetVoteService getVoteService;

	private final HotVoteRepository hotVoteRepository;

	private int size = 5;

	public GetVoteListResponse.MainAndFinishPage getVoteList(final Long userId, final int page) {
		int beforeHour = 1;
		int interval = 1;
		// 핫게시판에 반영할 1시간전을 계산
		List<LocalDateTime> lastTime = findLastTime(beforeHour, interval);
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);
		// repository를 통해 찾음
		Slice<VoteEntity> voteEntitySlice =
				hotVoteRepository.findByTimeLimitAndDecisionCount(
						lastTime.get(0), lastTime.get(1), pageable);
		// entity list >> dto list
		List<VoteDto> voteDtoList = voteDtoConverter(voteEntitySlice, userId);
		// 마지막 페이지인지 검사
		boolean isLast = true;
		// responseDto로 변환 후 return
		return new GetVoteListResponse.MainAndFinishPage(voteDtoList, isLast);
	}

	protected List<LocalDateTime> findLastTime(int before, int interval) {
		LocalDateTime startTime =
				LocalDateTime.now().minusHours(before).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime endTime = startTime.plusHours(interval);
		List<LocalDateTime> timeSet = new ArrayList<>();
		timeSet.add(startTime);
		timeSet.add(endTime);
		return timeSet;
	}

	protected List<VoteDto> voteDtoConverter(Slice<VoteEntity> voteEntitySlice, Long userId) {
		List<VoteDto> voteDtoList = new ArrayList<>();

		for (VoteEntity voteEntity : voteEntitySlice) {
			VoteDto voteDto = getVoteService.getVote(voteEntity, userId);
			voteDtoList.add(voteDto);
		}
		return voteDtoList;
	}
}
