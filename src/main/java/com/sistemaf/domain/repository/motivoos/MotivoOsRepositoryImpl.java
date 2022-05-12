package com.sistemaf.domain.repository.motivoos;

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

import com.sistemaf.domain.filter.MotivoOsFilter;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.model.MotivoOs_;

public class MotivoOsRepositoryImpl implements MotivoOsRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<MotivoOs> filtrar(MotivoOsFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MotivoOs> criteria = builder.createQuery(MotivoOs.class);
		
		Root<MotivoOs> root = criteria.from(MotivoOs.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<MotivoOs> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(MotivoOsFilter filter, CriteriaBuilder builder, Root<MotivoOs> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(MotivoOs_.descricao)), 
						"%"+filter.getDescricao().toLowerCase()+"%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(MotivoOsFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<MotivoOs> root = criteria.from(MotivoOs.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoedePaginacao(TypedQuery<MotivoOs> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}

}
