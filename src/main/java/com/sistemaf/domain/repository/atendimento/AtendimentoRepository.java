package com.sistemaf.domain.repository.atendimento;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, AtendimentoRepositoryQuery {

}
