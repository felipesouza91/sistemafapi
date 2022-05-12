package com.sistemaf.domain.repository.cidade;

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

import com.sistemaf.domain.filter.CidadeFilter;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.model.Cidade_;
import com.sistemaf.infrastructure.util.interfaces.FilterUtil;
import com.sistemaf.infrastructure.util.interfaces.PageableUtil;

public class CidadeRepositoryImpl extends PageableUtil implements CidadeRepositoryQuery, FilterUtil<CidadeFilter, Cidade> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		Predicate[] predicates = criarRestricoes(cidadeFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, 
				total(manager, predicates, root, Cidade.class));
	}

	@Override
	 public Predicate[] criarRestricoes(CidadeFilter cidadeFilter, CriteriaBuilder builder, Root<Cidade> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(cidadeFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Cidade_.nome)), "%"+cidadeFilter.getNome().toLowerCase()+"%" ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

}
