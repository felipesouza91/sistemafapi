package com.sistemaf.domain.repository.estoque.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.sistemaf.domain.filter.ProdutoFiltro;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.projection.ResumoProduto;

public interface ProdutoRepositoryQuery {

	public Optional<Produto> findByModeloWithoutSpaces(String name);
	public Page<Produto> filtrar(ProdutoFiltro filtro, Pageable page);
	public Page<ResumoProduto> resumo(ProdutoFiltro filtro, Pageable page);
}
