package com.sistemaf.domain.repository.dvr;

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

import com.sistemaf.domain.filter.DvrFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.model.Dvr_;

public class DvrRepositoryImpl implements DvrRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Dvr> filtrar(DvrFilter dvrFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dvr> criteria = builder.createQuery(Dvr.class);
		Root<Dvr> root = criteria.from(Dvr.class);
		
		Predicate[] predicates = criarRestricoes(dvrFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Dvr> query = manager.createQuery(criteria);
		adicionarRestricao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(dvrFilter));
		
	}
	
	private Predicate[] criarRestricoes(DvrFilter dvrFilter, CriteriaBuilder builder, Root<Dvr> root) {
		List<Predicate> predicates = new ArrayList<>();
 	
		if(!StringUtils.isEmpty(dvrFilter.getCodCliente())) {
			
			Join<Dvr, Cliente> join = root.join(Dvr_.cliente);
			predicates.add(builder.equal(
					join.get(Cliente_.id), 
						dvrFilter.getCodCliente()));
		}
		
		if(!StringUtils.isEmpty(dvrFilter.getFabricante())) {
			
			predicates.add(builder.like(
					builder.lower(root.get(Dvr_.fabricante)),
						"%"+dvrFilter.getFabricante().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(dvrFilter.getModelo())) {
			
			predicates.add(builder.like(
					builder.lower(root.get(Dvr_.modeloDvr)),
						"%"+dvrFilter.getModelo().toLowerCase()+"%"));
		}

		if(!StringUtils.isEmpty(dvrFilter.getNomeCliente())) {
			
			Join<Dvr, Cliente> join = root.join(Dvr_.cliente);
			predicates.add(builder.like(
					builder.lower(join.get(Cliente_.fantazia)), 
						"%"+dvrFilter.getNomeCliente().toLowerCase()+"%"));
		}
				
		if(!StringUtils.isEmpty(dvrFilter.getNumeroSerie())) {
			
			predicates.add(builder.like(
					builder.lower(root.get(Dvr_.numeroSerie)),
						"%"+dvrFilter.getNumeroSerie().toLowerCase()+"%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	private Long total(DvrFilter dvrFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Dvr> root = criteria.from(Dvr.class);
		Predicate[] predicates = criarRestricoes(dvrFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adicionarRestricao(TypedQuery<Dvr> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}

}
