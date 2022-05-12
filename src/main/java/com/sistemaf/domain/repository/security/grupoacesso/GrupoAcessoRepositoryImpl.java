package com.sistemaf.domain.repository.security.grupoacesso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.GrupoAcesso_;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;

public class GrupoAcessoRepositoryImpl implements GrupoAcessoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ResumoGrupoAcesso> listarResumo() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoGrupoAcesso> criteria = builder.createQuery(ResumoGrupoAcesso.class);
		Root<GrupoAcesso> root = criteria.from(GrupoAcesso.class);
		
		criteria.select(builder.construct(ResumoGrupoAcesso.class,
				root.get(GrupoAcesso_.id), root.get(GrupoAcesso_.ativo),
				root.get(GrupoAcesso_.descricao)));
		
		TypedQuery<ResumoGrupoAcesso> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
