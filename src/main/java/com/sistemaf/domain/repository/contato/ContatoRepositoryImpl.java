package com.sistemaf.domain.repository.contato;

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

import com.sistemaf.domain.filter.ContatoFilter;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Contato;
import com.sistemaf.domain.model.Contato_;
import com.sistemaf.domain.model.Funcao_;
import com.sistemaf.infrastructure.util.interfaces.FilterUtil;
import com.sistemaf.infrastructure.util.interfaces.PageableUtil;

public class ContatoRepositoryImpl extends PageableUtil
	implements ContatoRepositoryQuery, FilterUtil<ContatoFilter, Contato> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Contato> filtrar(ContatoFilter filter, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
		Root<Contato> root =criteria.from(Contato.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<Contato> query = manager.createQuery(criteria);
		super.adicionarRestricoesDePaginacao(query, page);
		
		return new PageImpl<>(query.getResultList(), page,
				super.total(manager, predicates, root, Contato.class));
	}

	@Override
	public Predicate[] criarRestricoes(ContatoFilter filter, CriteriaBuilder builder, Root<Contato> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filter.getCodCliente())) {
			predicates.add(builder.equal(root.get(Contato_.cliente).get(Cliente_.id), filter.getCodCliente()));
		}
		if(!StringUtils.isEmpty(filter.getIdFuncao())) {
			predicates.add(builder.equal(root.get(Contato_.funcao).get(Funcao_.id), filter.getIdFuncao()));
		}
		if(!StringUtils.isEmpty(filter.getNomeContato())) {
			predicates.add(builder.like(builder.lower(root.get(Contato_.nome)),
						"%"+filter.getNomeContato().toLowerCase()+"%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
