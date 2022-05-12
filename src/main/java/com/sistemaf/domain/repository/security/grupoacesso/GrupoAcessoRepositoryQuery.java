package com.sistemaf.domain.repository.security.grupoacesso;

import java.util.List;

import com.sistemaf.domain.projection.ResumoGrupoAcesso;

public interface GrupoAcessoRepositoryQuery {
	
	public List<ResumoGrupoAcesso> listarResumo();
}
