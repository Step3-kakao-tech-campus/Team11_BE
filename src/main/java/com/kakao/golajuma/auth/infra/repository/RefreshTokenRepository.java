package com.kakao.golajuma.auth.infra.repository;

import com.kakao.golajuma.auth.domain.model.RefreshToken;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RefreshTokenRepository {

	private RedisTemplate<Long, String> redisTemplate;

	@Value("${redis.timeToLive}")
	private long ttl;

	public RefreshTokenRepository(final RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(final RefreshToken refreshToken) {
		ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(refreshToken.getUserId(), refreshToken.getRefreshToken());
		redisTemplate.expire(refreshToken.getUserId(), ttl, TimeUnit.SECONDS);
	}

	public Optional<RefreshToken> findById(final Long userId) {
		ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
		String refreshToken = valueOperations.get(userId);

		if (Objects.isNull(refreshToken)) {
			return Optional.empty();
		}

		return Optional.of(new RefreshToken(refreshToken, userId));
	}
}
