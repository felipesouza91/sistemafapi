package com.sistemaf.domain.repository.bairro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.sistemaf.domain.filter.BairroFilter;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.Bairro_;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.model.Cidade_;

public class BairroRepositoryImpl implements BairroRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Bairro> filtrar(BairroFilter bairroFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Bairro> criteria = builder.createQuery(Bairro.class);
		Root<Bairro> root = criteria.from(Bairro.class);
		
		Predicate[] predicates = criarRestricoes(bairroFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Bairro> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(bairroFilter));
	}

	private Predicate[] criarRestricoes(BairroFilter bairroFilter, CriteriaBuilder builder, Root<Bairro> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(bairroFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Bairro_.nome)), "%"+bairroFilter.getNome().toLowerCase()+"%" ));
		}
		if(!StringUtils.isEmpty(bairroFilter.getNomeCidade())) {
			Join<Bairro, Cidade> join = root.join(Bairro_.cidade);
			predicates.add(builder.like(join.get(Cidade_.nome), "%"+bairroFilter.getNomeCidade().toLowerCase()+"%" ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(BairroFilter bairroFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Bairro> root = criteria.from(Bairro.class);
		Predicate[] predicates = criarRestricoes(bairroFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoedePaginacao(TypedQuery<Bairro> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}

}
