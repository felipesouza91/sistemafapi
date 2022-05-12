package com.sistemaf.domain.repository.security.grupoacesso;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.GrupoAcesso;

public interface GrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long>, GrupoAcessoRepositoryQuery{

}
