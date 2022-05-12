package com.sistemaf.domain.repository.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.CidadeFilter;
import com.sistemaf.domain.model.Cidade;


public interface CidadeRepositoryQuery {
	
	public Page<Cidade> filtrar(CidadeFilter cidadeFilterFilter, Pageable pageable);
}
