package com.sistemaf.domain.repository.fechamentoos;

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

import com.sistemaf.domain.filter.FechamentoOsFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.FechamentoOs;
import com.sistemaf.domain.model.FechamentoOs_;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.model.OrdemServico_;

public class FechamentoOsRepositoryImpl implements FechamentoOsRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<FechamentoOs> filtrar(FechamentoOsFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FechamentoOs> criteria = builder.createQuery(FechamentoOs.class);
		
		Root<FechamentoOs> root = criteria.from(FechamentoOs.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<FechamentoOs> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(FechamentoOsFilter filter, CriteriaBuilder builder, Root<FechamentoOs> root) {
		List<Predicate> predicates = new ArrayList<>();
		Join<FechamentoOs, OrdemServico> joinOrdemServico = root.join(FechamentoOs_.os);
		Join<OrdemServico, Cliente> joinClinete = joinOrdemServico.join(OrdemServico_.cliente);
		
		if(!StringUtils.isEmpty(filter.getCodigoOs())) {
			predicates.add(builder.equal(
					joinOrdemServico.get(OrdemServico_.id), filter.getCodigoOs()));
		}
		if(!StringUtils.isEmpty(filter.getCodigoCliente())) {
			predicates.add(builder.equal(
					joinClinete.get(Cliente_.id), filter.getCodigoCliente()));
		}
		if(!StringUtils.isEmpty(filter.getNomeTecnico())) {
			predicates.add(builder.like(
					builder.lower(root.get(FechamentoOs_.tecnico)),
						"%"+filter.getNomeTecnico().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getNomeCliente())) {
			predicates.add(builder.like(
					builder.lower(joinClinete.get(Cliente_.fantazia)),
						"%"+filter.getNomeCliente().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getMotivoFechamento())) {
			predicates.add(builder.like(
					builder.lower(root.get(FechamentoOs_.motivoFechamento)),
						"%"+filter.getMotivoFechamento().toLowerCase()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getDataFachamentoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(FechamentoOs_.dataFechamento),
					convertToLocalDateTimeViaInstant(filter.getDataFachamentoDe())));
		}
		if(!StringUtils.isEmpty(filter.getDataFechamentoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(FechamentoOs_.dataFechamento),
					convertToLocalDateTimeViaInstant(filter.getDataFachamentoDe())));
		}
		if(!StringUtils.isEmpty(filter.getDataVisitaDe())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(FechamentoOs_.dataVisita),
					convertToLocalDateTimeViaInstant(filter.getDataFachamentoDe())));
		}
		if(!StringUtils.isEmpty(filter.getDataVisitaAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(FechamentoOs_.dataVisita),
					convertToLocalDateTimeViaInstant(filter.getDataFachamentoDe())));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(FechamentoOsFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<FechamentoOs> root = criteria.from(FechamentoOs.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoedePaginacao(TypedQuery<FechamentoOs> query, Pageable pageable) {
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
