package com.sistemaf.domain.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery{

    Optional<Cliente> findByCodigoParticao(String codigoParticao);

}
