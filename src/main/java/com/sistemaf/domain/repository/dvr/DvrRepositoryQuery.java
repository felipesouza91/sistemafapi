package com.sistemaf.domain.repository.dvr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.DvrFilter;
import com.sistemaf.domain.model.Dvr;

public interface DvrRepositoryQuery {

	public Page<Dvr> filtrar(DvrFilter dvrFilter, Pageable pageable);
}
