package com.sistemaf.domain.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.ClienteInformacao;

public interface ClienteInformacaoQuery {

	public Page<ClienteInformacao> filtrar(Long clientId, InformacaoFilter filer, Pageable pageable);
	
}
