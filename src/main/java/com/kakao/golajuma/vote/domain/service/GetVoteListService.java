package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
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

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteListService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;
	private final DecisionRepository decisionRepository;

	static int page = 0;
	static int size = 5;

	public GetVoteListResponse.MainAndFinishPage getVoteList(
			Long userId, int page, Sort sort, Active active, Category category) {
		/*
		투표 중 active = continue 이고, createdDate가 최신순으로 정렬하여 가져와서 보여준다
		사용자의 id를 가져와서 참여한 투표와 참여하지 않은 투표를 다른 데이터 형식으로 반환한다.
		*/
		this.page = page;

		// 1. vote list 를 가져온다
		// 투표 리스트 가져 오는 방법
		// 정렬 기준
		// 1. 메인페이지 or 완료된 페이지 active
		// 2. 전체 카테고리 or 세부 카테고리
		// 3. 최신순 or 인기순
		Slice<VoteEntity> voteList = getVoteListByRequest(active, category, sort);

		List<VoteDto> votes = new ArrayList<>();
		for (VoteEntity vote : voteList) {
			VoteDto voteDto = getVoteService.getVote(vote, userId);
			votes.add(voteDto);
		}
		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new GetVoteListResponse.MainAndFinishPage(votes, isLast);
	}

	private Slice<VoteEntity> getVoteListByRequest(Active active, Category category, Sort sort) {
		// 메인페이지 요청인지 완료된 페이지 요청인지 검사
		if (Active.isContinueRequest(active)) {
			return findContinueVotes(category, sort);
		}
		return findCompleteVotes(category, sort);
	}

	private Slice<VoteEntity> findCompleteVotes(Category category, Sort sort) {
		// 카테고리 요청 확인
		if (Category.isTotalRequest(category)) {
			return completeOrderBySort(sort);
		}
		return completeByCategoryOrderBySort(category, sort);
	}

	private Slice<VoteEntity> completeOrderBySort(Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		// 정렬 요청 확인
		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllFinishVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllFinishVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> completeByCategoryOrderBySort(Category category, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);

		LocalDateTime now = LocalDateTime.now();

		// 정렬 요청 확인
		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllFinishVotesByCategoryOrderByCreatedDate(now, category, pageable);
		}
		return voteRepository.findAllFinishVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}

	private Slice<VoteEntity> findContinueVotes(Category category, Sort sort) {
		// 카테고리 요청 확인
		if (Category.isTotalRequest(category)) {
			return continueOrderBySort(sort);
		}
		return continueByCategoryOrderBySort(category, sort);
	}

	private Slice<VoteEntity> continueOrderBySort(Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		// 정렬 요청 확인
		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllContinueVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllContinueVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> continueByCategoryOrderBySort(Category category, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		// 정렬 요청 확인
		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllContinueVotesByCategoryOrderByCreatedDate(
					now, category, pageable);
		}
		return voteRepository.findAllContinueVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByParticipate(Long userId) {
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 투표한 투표 리스트를 decision 레포에서 찾아야함
		List<VoteEntity> voteList = voteRepository.findAllParticipateListByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByAsk(Long userId) {
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 올린 투표를 가져옴
		List<VoteEntity> voteList = voteRepository.findAllByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;
	}
}
