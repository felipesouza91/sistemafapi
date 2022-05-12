package com.sistemaf.domain.repository.grupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.GrupoFilter;
import com.sistemaf.domain.model.Grupo;

public interface GrupoRepositoryQuery {
	
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable);
}
