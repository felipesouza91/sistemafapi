package com.sistemaf.domain.repository.verificacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.VerificarGravacaoFilter;
import com.sistemaf.domain.model.VerificacaoGravacao;
import com.sistemaf.domain.projection.ResumoVerificacaoGravacao;

public interface VerificacaoGravacaoRepositoryQuery {

	public Page<VerificacaoGravacao> filtrar(VerificarGravacaoFilter filter, Pageable pageable);
	public Page<ResumoVerificacaoGravacao> resumir(VerificarGravacaoFilter filter, Pageable pageable);
}
