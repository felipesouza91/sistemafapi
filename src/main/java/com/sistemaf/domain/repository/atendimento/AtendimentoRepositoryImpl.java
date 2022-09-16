package com.sistemaf.domain.repository.atendimento;

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

import com.sistemaf.domain.filter.AtendimentoFilter;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.model.Atendimento_;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Usuario_;
import com.sistemaf.domain.projection.ResumoAtendimento;

public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Atendimento> filtrar(AtendimentoFilter filter, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<Atendimento> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page, total(filter));
	}

	
	@Override
	public Page<ResumoAtendimento> resumo(AtendimentoFilter filter, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoAtendimento> criteria = builder.createQuery(ResumoAtendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		criteria.select(builder.construct(ResumoAtendimento.class, root.get(Atendimento_.id),
				root.get(Atendimento_.descricaoProblema), root.get(Atendimento_.cliente).get(Cliente_.id),
				root.get(Atendimento_.cliente).get(Cliente_.fantazia), root.get(Atendimento_.dataInicio),
				root.get(Atendimento_.dataTermino),root.get(Atendimento_.usuarioInicio).get(Usuario_.id),
				root.get(Atendimento_.usuarioInicio).get(Usuario_.nome), root.get(Atendimento_.usuarioTermino).get(Usuario_.id),
				root.get(Atendimento_.solicitante)));
		
		
		TypedQuery<ResumoAtendimento> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page, total(filter));
	}
	
	private Predicate[] criarRestricoes(AtendimentoFilter filter, CriteriaBuilder builder, Root<Atendimento> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(filter.getIdCliente() != null ) {
			predicates.add(builder.equal(
					root.get(Atendimento_.cliente).get(Cliente_.id),
						filter.getIdCliente()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(AtendimentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoedePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual =pageable.getPageNumber(); 
		int totalPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalPagina;
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalPagina);
	}
	
}
