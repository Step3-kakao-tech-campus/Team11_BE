package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteJPARepository extends JpaRepository<VoteEntity, Integer> {}
