package com.sistemaf.domain.repository.bairro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.Cidade;

public interface BairroRepository extends JpaRepository<Bairro, Long>, BairroRepositoryQuery{

	public Optional<Bairro> findByNome(String nome);
	public Optional<Bairro> findByCidadeAndNome(Cidade cidade, String nome);
}
