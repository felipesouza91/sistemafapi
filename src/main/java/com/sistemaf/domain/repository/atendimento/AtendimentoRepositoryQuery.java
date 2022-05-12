package com.sistemaf.domain.repository.atendimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.AtendimentoFilter;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.projection.ResumoAtendimento;

public interface AtendimentoRepositoryQuery {

	public Page<Atendimento> filtrar(AtendimentoFilter filter, Pageable page);
	public Page<ResumoAtendimento> resumo(AtendimentoFilter filter, Pageable page);
}
