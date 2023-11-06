package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.SearchVoteListResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchVoteListService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;
	static int page = 0;
	static int size = 5;

	public SearchVoteListResponse searchVoteList(
			Long userId, int page, String keyword, Sort sort, Category category) {
		this.page = page;

		// 1. vote list 를 가져온다
		Slice<VoteEntity> voteList = findVotes(keyword, category, sort);

		List<VoteDto> votes =
				voteList.stream()
						.map(voteEntity -> getVoteService.getVote(voteEntity, userId))
						.collect(Collectors.toList());

		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new SearchVoteListResponse(votes, isLast);
	}

	private Slice<VoteEntity> findVotes(String keyword, Category category, Sort sort) {
		// 카테고리 요청 확인
		if (Category.isTotalRequest(category)) {
			return OrderBySort(keyword, sort);
		}
		return ByCategoryOrderBySort(keyword, category, sort);
	}

	private Slice<VoteEntity> OrderBySort(String keyword, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.searchVotesOrderByCreatedDate(keyword, pageable);
		}
		return voteRepository.searchVotesOrderByVoteTotalCount(keyword, pageable);
	}

	private Slice<VoteEntity> ByCategoryOrderBySort(String keyword, Category category, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.searchVotesByCategoryOrderByCreatedDate(keyword, category, pageable);
		}
		return voteRepository.searchVotesByCategoryOrderByVoteTotalCount(keyword, category, pageable);
	}
}
