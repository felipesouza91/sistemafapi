package com.sistemaf.domain.repository.motivoos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.MotivoOs;

public interface MotivoOsRepository extends JpaRepository<MotivoOs, Long>, MotivoOsRepositoryQuery {

}
