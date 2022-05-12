package com.sistemaf.domain.repository.grupo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.sistemaf.domain.filter.GrupoFilter;
import com.sistemaf.domain.model.Grupo;
import com.sistemaf.domain.model.Grupo_;

public class GrupoRepositoryImpl implements GrupoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Grupo> criteria = builder.createQuery(Grupo.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		Predicate[] predicates = criarRestricoes(grupoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Grupo> query = manager.createQuery(criteria);
		adicionarRestricao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(grupoFilter));
	}

	private Predicate[] criarRestricoes(GrupoFilter grupoFilter, CriteriaBuilder builder, Root<Grupo> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(grupoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Grupo_.nome)), "%"+grupoFilter.getNome().toLowerCase()+"%" ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(GrupoFilter grupoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Grupo> root = criteria.from(Grupo.class);
		Predicate[] predicates = criarRestricoes(grupoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adicionarRestricao(TypedQuery<Grupo> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}

}
