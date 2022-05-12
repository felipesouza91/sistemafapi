package com.sistemaf.domain.repository.cliente;

import com.sistemaf.domain.model.ClienteInformacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteInformacaoRepository extends JpaRepository<ClienteInformacao, Long>, ClienteInformacaoQuery{

}
