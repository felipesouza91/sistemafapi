package com.sistemaf.domain.repository.verificacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import com.sistemaf.domain.filter.VerificarGravacaoFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.model.Dvr_;
import com.sistemaf.domain.model.Usuario_;
import com.sistemaf.domain.model.VerificacaoGravacao;
import com.sistemaf.domain.model.VerificacaoGravacao_;
import com.sistemaf.domain.projection.ResumoVerificacaoGravacao;

public class VerificacaoGravacaoRepositoryImpl implements VerificacaoGravacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<VerificacaoGravacao> filtrar(VerificarGravacaoFilter filter, Pageable pageable) {
		VerificarGravacaoFilter verificarGravacaoFilter = (VerificarGravacaoFilter) filter;
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<VerificacaoGravacao> criteria = builder.createQuery(VerificacaoGravacao.class);
		
		Root<VerificacaoGravacao> root = criteria.from(VerificacaoGravacao.class);
		
		Predicate[] predicates = criarRestricoes(verificarGravacaoFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<VerificacaoGravacao> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(verificarGravacaoFilter));
		
	}
	
	@Override
	public Page<ResumoVerificacaoGravacao> resumir(VerificarGravacaoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoVerificacaoGravacao> criteria = builder.createQuery(ResumoVerificacaoGravacao.class);
		Root<VerificacaoGravacao> root = criteria.from(VerificacaoGravacao.class);
		criteria.select(builder.construct(ResumoVerificacaoGravacao.class, root.get(VerificacaoGravacao_.id),
				root.get(VerificacaoGravacao_.status), root.get(VerificacaoGravacao_.hd),
				root.get(VerificacaoGravacao_.qtdGravacao), root.get(VerificacaoGravacao_.dataTeste),
				root.get(VerificacaoGravacao_.dvr).get(Dvr_.id), root.get(VerificacaoGravacao_.dvr).get(Dvr_.fabricante), 
				root.get(VerificacaoGravacao_.dvr).get(Dvr_.modeloDvr),
				root.get(VerificacaoGravacao_.usuario).get(Usuario_.nome),
				root.get(VerificacaoGravacao_.dvr).get(Dvr_.cliente).get(Cliente_.id),
				root.get(VerificacaoGravacao_.dvr).get(Dvr_.cliente).get(Cliente_.fantazia)));
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<ResumoVerificacaoGravacao> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	private Predicate[] criarRestricoes(VerificarGravacaoFilter filter, CriteriaBuilder builder, Root<VerificacaoGravacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		Join<VerificacaoGravacao, Dvr> joinDvr = root.join(VerificacaoGravacao_.dvr);
		Join<Dvr, Cliente> joinCliente = joinDvr.join(Dvr_.cliente);

		
		if(!StringUtils.isEmpty(filter.getCodigoDvr())) {
			predicates.add(builder.equal(joinDvr.get(Dvr_.id), filter.getCodigoDvr()));
		}
		
		if(!StringUtils.isEmpty(filter.getCodigoCliente())) {
			predicates.add(builder.equal(joinCliente.get(Cliente_.id), filter.getCodigoCliente()));
		}
		
		if(!StringUtils.isEmpty(filter.getNomeCliente())) {
			predicates.add(builder.like(
					builder.lower(joinCliente.get(Cliente_.fantazia)), 
						"%"+filter.getNomeCliente().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(filter.getDataVerificacaoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(VerificacaoGravacao_.dataTeste), 
					convertToLocalDateTimeViaInstant(filter.getDataVerificacaoDe())));
		}
		
		if(!StringUtils.isEmpty(filter.getDataVerificacaoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(VerificacaoGravacao_.dataTeste),
					convertToLocalDateTimeViaInstant(filter.getDataVerificacaoAte())));
		}	
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	private Long total(VerificarGravacaoFilter filter) {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<VerificacaoGravacao> root = criteria.from(VerificacaoGravacao.class);
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
	
	private LocalDateTime convertToLocalDateTimeViaInstant(LocalDate dateToConvert) {
	    return dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
}
