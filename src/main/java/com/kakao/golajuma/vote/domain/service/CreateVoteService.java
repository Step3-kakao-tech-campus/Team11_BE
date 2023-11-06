package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.util.ImageUploader;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateVoteService {

	private final VoteRepository voteRepository;
	private final OptionRepository optionRepository;
	private final ImageUploader imageUploader;

	@Transactional
	public CreateVoteResponse createVote(CreateVoteRequest request, Long userId) {
		VoteEntity vote = VoteEntity.createEntity(request, userId);
		voteRepository.save(vote);
		Long voteId = vote.getId();
		for (CreateVoteRequest.OptionDto optionDto : request.getOptions()) {
			OptionEntity option = createOption(optionDto, voteId);
			optionRepository.save(option);
		}
		return new CreateVoteResponse(voteId);
	}

	private OptionEntity createOption(CreateVoteRequest.OptionDto optionDto, Long voteId) {
		if (optionDto.getImage() != null) {
			String imagePath = imageUploader.uploadImageByBase64(optionDto);
			return OptionEntity.createEntityWithImage(optionDto, imagePath, voteId);
		}
		return OptionEntity.createEntity(optionDto, voteId);
	}
}
