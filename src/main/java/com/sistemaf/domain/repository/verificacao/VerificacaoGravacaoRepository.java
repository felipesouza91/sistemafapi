package com.sistemaf.domain.repository.verificacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.VerificacaoGravacao;

public interface VerificacaoGravacaoRepository extends JpaRepository<VerificacaoGravacao, Long>,VerificacaoGravacaoRepositoryQuery{

}
