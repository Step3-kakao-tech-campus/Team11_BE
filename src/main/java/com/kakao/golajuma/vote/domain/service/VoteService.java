package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.NullException;
import com.kakao.golajuma.vote.domain.exception.OptionNumException;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionJPARepository;
import com.kakao.golajuma.vote.infra.repository.VoteJPARepository;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VoteService {

	private final VoteJPARepository voteJPARepository;
	private final OptionJPARepository optionJPARepository;

	@Transactional
	public CreateVoteResponse createVote(CreateVoteRequest requestDto) {
		boolean exit;
		// 1. 투표 제목이 있는지 확인 후 예외처리
		if (requestDto.getTitle() == null) {
			throw new NullException("제목을 입력해주세요");
		}

		// 2. 옵션 개수가 2개 ~ 6개 인지 확인 후 예외처리
		int size = requestDto.getOptions().size();
		if (size < 2 || size > 6) {
			throw new OptionNumException("선택지의 개수는 2개 이하 6개 이상이어야 합니다.");
		}
		// 3. 옵션 이름이 있는지 확인
		for (CreateVoteRequest.OptionDTO option : requestDto.getOptions()) {
			if (option.getName() == null) {
				throw new NullException("옵션 이름을 입력해주세요.");
			}
		}

		// 4. 카테고리가 맞는지 확인
		//        Optional<Category> category = Category.findCategory(requestDto.getCategory());
		//        if(!category.isPresent()){
		//            throw new CategoryException("해당 카테고리를 찾을 수 없습니다.");
		//        }

		System.out.println(requestDto);
		VoteEntity vote = voteJPARepository.save(requestDto.toEntity());
		long voteId = vote.getId();
		for (CreateVoteRequest.OptionDTO option : requestDto.getOptions()) {
			optionJPARepository.save(option.toEntity(voteId));
		}
		return new CreateVoteResponse(voteId);
	}
}
