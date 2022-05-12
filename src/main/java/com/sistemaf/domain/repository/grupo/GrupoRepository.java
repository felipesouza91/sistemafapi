package com.sistemaf.domain.repository.grupo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>,GrupoRepositoryQuery{

}
