package com.sistemaf.domain.repository.estoque.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery{

}
