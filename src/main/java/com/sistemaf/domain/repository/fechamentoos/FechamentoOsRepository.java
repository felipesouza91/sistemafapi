package com.sistemaf.domain.repository.fechamentoos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.FechamentoOs;

public interface FechamentoOsRepository extends JpaRepository<FechamentoOs, Long>, FechamentoOsRepositoryQuery {

}
