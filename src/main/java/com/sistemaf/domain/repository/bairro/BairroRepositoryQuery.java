package com.sistemaf.domain.repository.bairro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.BairroFilter;
import com.sistemaf.domain.model.Bairro;

public interface BairroRepositoryQuery {

	public Page<Bairro> filtrar(BairroFilter bairroFilter, Pageable pageable);
}
