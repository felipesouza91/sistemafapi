package com.sistemaf.domain.repository.estoque.fabricante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sistemaf.domain.model.Fabricante;

public interface FabricanteRepository extends PagingAndSortingRepository<Fabricante, Long>{

	public Page<Fabricante> findByDescricaoIgnoreCaseContaining(String nome, Pageable pageable);
}
