package com.sistemaf.domain.repository.contato;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.sistemaf.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryQuery{

  public Optional<Contato> findByNome(String name);

  public Optional<Contato> findByTelefone(String telefone);
}
