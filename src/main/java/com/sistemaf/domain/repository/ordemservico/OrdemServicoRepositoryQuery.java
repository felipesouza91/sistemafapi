package com.sistemaf.domain.repository.ordemservico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.OrdemServicoFilter;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.projection.ResumOrdemServico;

public interface OrdemServicoRepositoryQuery {

	public Page<OrdemServico> filtrar(OrdemServicoFilter filter, Pageable pageable);
	public Page<ResumOrdemServico> resumo(OrdemServicoFilter filter, Pageable pageable);
}
