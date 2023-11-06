package com.kakao.golajuma.auth.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReadUserProfileServiceTest {
	@InjectMocks private ReadUserProfileService readUserProfileService;
	@Mock private UserRepository userRepository;
	@Mock private VoteRepository voteRepository;

	@Nested
	@DisplayName("mypage 요청 성공 시 유저 데이터를 반환한다.")
	class success_read_user_profile {
		@Test
		@DisplayName("유저의 데이터를 가져온다.")
		void success_read_user_entity() {
			// given
			UserEntity userEntity = UserEntity.builder().build();
			when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
			// when
			UserEntity result = readUserProfileService.validateUserEntity(anyLong());
			// then
			assertThat(result).isEqualTo(userEntity);
		}

		@Test
		@DisplayName("유저가 생성한 투표의 개수를 센다")
		void success_count_created_vote() {
			// given
			List<VoteEntity> voteEntity = new ArrayList<>();
			when(voteRepository.findAllByUserId(anyLong())).thenReturn(voteEntity);
			// when
			int result = readUserProfileService.countCreatedVote(anyLong());
			// then
			assertThat(result).isEqualTo(voteEntity.size());
		}

		@Test
		@DisplayName("유저가 참가한 투표의 개수를 센다")
		void success_count_participated_vote() {
			// given
			List<VoteEntity> voteEntity = new ArrayList<>();
			when(voteRepository.findAllParticipateListByUserId(anyLong())).thenReturn(voteEntity);
			// when
			int result = readUserProfileService.countParticipatedVote(anyLong());
			// then
			assertThat(result).isEqualTo(voteEntity.size());
		}

		@Test
		@DisplayName("dto에 맞는 형태로 데이터를 삽입한다.")
		void success_convert_data() {
			UserEntity userEntity = UserEntity.builder().build();
			int createVoteCount = 3;
			int participateVoteCount = 3;
			UserProfileResponse response =
					readUserProfileService.userProfileConverter(
							userEntity, createVoteCount, participateVoteCount);
			assertThat(response.getEmail()).isEqualTo(userEntity.getNickname());
			assertThat(response.getNickName()).isEqualTo(userEntity.getNickname());
			assertThat(response.getImage()).isEqualTo("미구현");
			assertThat(response.getCreateVoteCount()).isEqualTo(createVoteCount);
			assertThat(response.getParticipateVoteCount()).isEqualTo(participateVoteCount);
		}
	}

	@Nested
	@DisplayName("mypage 요청이 실패 시 예외를 반환한다.")
	class fail_read_user_profile {
		@Test
		@DisplayName("존재하지 않는 유저의 데이터를 요청하는 경우")
		void null_read_user_entity() {
			// given
			UserEntity userEntity = UserEntity.builder().build();
			when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
			// when & then
			assertThrows(
					NullPointerException.class, () -> readUserProfileService.validateUserEntity(anyLong()));
		}
	}
}
