package com.sistemaf.domain.repository.motivoos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.MotivoOsFilter;
import com.sistemaf.domain.model.MotivoOs;

public interface MotivoOsRepositoryQuery {
	
	public Page<MotivoOs> filtrar(MotivoOsFilter filter, Pageable pageable);

}
