package com.sistemaf.domain.repository.cliente;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.ClienteInformacao;
import com.sistemaf.domain.model.ClienteInformacao_;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Usuario_;
import com.sistemaf.infrastructure.util.interfaces.FilterUtil;
import com.sistemaf.infrastructure.util.interfaces.PageableUtil;

public class ClienteInformacaoRepositoryImpl extends PageableUtil
	implements ClienteInformacaoQuery, FilterUtil<InformacaoFilter, ClienteInformacao>{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ClienteInformacao> filtrar(Long clientId, InformacaoFilter filer, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteInformacao> criteria = builder.createQuery(ClienteInformacao.class);
		Root<ClienteInformacao> root = criteria.from(ClienteInformacao.class);
		Predicate[] predicates = this.wrapCreateRestirctions(clientId, filer, builder, root);
		criteria.where(predicates);
		TypedQuery<ClienteInformacao> query = manager.createQuery(criteria);
		super.adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable,
				super.total(manager, predicates, root, ClienteInformacao.class));
	}

	public Predicate[] wrapCreateRestirctions(Long clientId ,InformacaoFilter filter, CriteriaBuilder builder, Root<ClienteInformacao> root) {
		List<Predicate> newPredicates = new ArrayList<>();
		newPredicates.addAll(Arrays.asList(this.criarRestricoes(filter,builder,root)));
		newPredicates.add(builder.equal(root.get(ClienteInformacao_.cliente).get(Cliente_.id), clientId) );
		return newPredicates.toArray(new Predicate[newPredicates.size()]);
	}

	@Override
	public Predicate[] criarRestricoes(InformacaoFilter filter, CriteriaBuilder builder, Root<ClienteInformacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filter.getIdUserCreate())) {
			predicates.add(builder.like(builder.lower(root.get(ClienteInformacao_.createdBy).get(Usuario_.nome)),
					"%"+filter.getIdUserCreate().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getIdUserCreate())) {
			predicates.add(builder.equal(root.get(ClienteInformacao_.createdBy).get(Usuario_.id), filter.getIdUserCreate()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
