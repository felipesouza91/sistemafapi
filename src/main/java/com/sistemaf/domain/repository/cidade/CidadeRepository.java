package com.sistemaf.domain.repository.cidade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>,CidadeRepositoryQuery{

}
