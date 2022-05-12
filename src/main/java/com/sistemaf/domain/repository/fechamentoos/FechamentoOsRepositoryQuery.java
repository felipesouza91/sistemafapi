package com.sistemaf.domain.repository.fechamentoos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.FechamentoOsFilter;
import com.sistemaf.domain.model.FechamentoOs;

public interface FechamentoOsRepositoryQuery {

	public Page<FechamentoOs> filtrar(FechamentoOsFilter filter, Pageable pageable);
	
}
