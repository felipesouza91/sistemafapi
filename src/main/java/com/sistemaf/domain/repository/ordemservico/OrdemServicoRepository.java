package com.sistemaf.domain.repository.ordemservico;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoRepositoryQuery{

}
