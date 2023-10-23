package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.web.dto.response.CountOptionDto;
import com.kakao.golajuma.vote.web.dto.response.OptionDto;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteService {
	private final OptionRepository optionJPARepository;
	private final UserRepository userRepository;
	private final DecisionRepository decisionRepository;

	public VoteDto getVote(VoteEntity vote, Long userId) {
		// 투표의 옵션을 찾는다
		List<OptionEntity> options = optionJPARepository.findAllByVoteId(vote.getId());
		// 작성자 찾기
		long writerId = vote.getUserId();
		UserEntity user = userRepository.findById(writerId).get();

		boolean participate;

		boolean isOwner = vote.isOwner(userId);

		Active active = vote.checkActive();

		List<? super OptionDto> optionList = new ArrayList<>();
		// 참여했는지 여부 판단
		List<Boolean> choiceList = checkChoiceOptions(options, userId);
		participate = checkParticipate(choiceList);

		// case 1 : 질문자, isOwner : true, participate : false, 옵션 카운트 표시
		// case 2 : 응답자, 참여 O, isOwner : false, participate : true, 옵션 카운트 표시
		// case 3 : 응답자, 참여 X, isOwner : false, participate : false, 옵션 카운트 미표시
		// 투표가 진행되고 있는 상태에서(on) && 주인이 아니고 && 참여하지 않았을때만 옵션 Count를 보여주지 않음 그냥 OptionDto
		if (vote.isOn() && !isOwner && !participate) {
			for (OptionEntity option : options) {
				OptionDto optionDto = OptionDto.makeOptionDto(option);
				optionList.add(optionDto);
			}
		} else {
			for (int i = 0; i < options.size(); i++) {
				OptionEntity option = options.get(i);
				boolean choice = choiceList.get(i);
				CountOptionDto countOptionDto =
						CountOptionDto.makeCountOptionDto(option, choice, vote.getVoteTotalCount());
				optionList.add(countOptionDto);
			}
		}
		String category = getCategory(vote);

		return VoteDto.makeDto(vote, user, active, isOwner, participate, category, optionList);
	}

	private String getCategory(VoteEntity vote) {
		return vote.getCategory().getCategory();
	}

	private List<Boolean> checkChoiceOptions(List<OptionEntity> options, long userId) {
		List<Boolean> chocieList = new ArrayList<>();
		for (OptionEntity option : options) {
			chocieList.add(checkChoiceOption(userId, option));
		}
		return chocieList;
	}

	private boolean checkChoiceOption(long userId, OptionEntity option) {
		//		 decision repo 탐색
		//		return decisionRepository.existByUserIdAndOptionId(userId, option.getId());
		return true;
	}

	private boolean checkParticipate(List<Boolean> choiceList) {
		return choiceList.contains(Boolean.TRUE);
	}
}
