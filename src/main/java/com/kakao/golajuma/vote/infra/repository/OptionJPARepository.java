package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionJPARepository extends JpaRepository<OptionEntity, Integer> {}
