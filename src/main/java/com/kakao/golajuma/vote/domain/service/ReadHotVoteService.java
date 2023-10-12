package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
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
public class ReadHotVoteService {

	private final HotVoteRepository hotVoteRepository;
	private final GetVoteService getVoteService;

	public GetVoteListResponse.MainAndFinishPage read(long userId, String active) {
		// 진행중인 투표(on) or 완료된 투표 요청 판단
		boolean on = true;

		// 1. vote list 를 가져온다
		Slice<VoteEntity> voteList = findByRepository(active);
		System.out.println(voteList);
		List<VoteDto> votes = new ArrayList<>();

		// 2. 각 vote 별로 vote option 을 찾는다 - slice 방식
		for (VoteEntity vote : voteList) {
			VoteDto voteDto = getVoteService.getVote(vote, userId, on);
			votes.add(voteDto);
		}

		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new GetVoteListResponse.MainAndFinishPage(votes, isLast);
	}

	public List<Boolean> checkChoiceOption(List<OptionEntity> options, long userId) {
		List<Boolean> choiceList = new ArrayList<>();
		//
		for (OptionEntity option : options) {
			long optionId = option.getId();
			// decision repo 에서 확인해야함
			//			if(decisionJPARepository.checkByUserIdAndOptionId(userId, optionId))
			//				choiceList.add(true);
			//			else choiceList.add(false);
			choiceList.add(true); // dummy data
		}
		return choiceList;
	}

	public Slice<VoteEntity> findByRepository(String active) {
		LocalDateTime startTime;
		LocalDateTime endTime;

		startTime = LocalDateTime.now().minusHours(18).withMinute(0).withSecond(0).withNano(0);
		endTime = startTime.plusHours(1);
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(1, 10);
		return hotVoteRepository.read(active, endTime, startTime, pageable);
	}
}