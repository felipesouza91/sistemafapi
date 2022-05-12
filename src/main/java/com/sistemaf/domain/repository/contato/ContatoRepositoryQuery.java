package com.sistemaf.domain.repository.contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.ContatoFilter;
import com.sistemaf.domain.model.Contato;

public interface ContatoRepositoryQuery {

	public Page<Contato> filtrar(ContatoFilter filter, Pageable page);
}
