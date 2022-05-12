package com.sistemaf.domain.repository.ordemservico;

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

import com.sistemaf.domain.filter.OrdemServicoFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.model.MotivoOs_;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.model.OrdemServico_;
import com.sistemaf.domain.projection.ResumOrdemServico;

public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<OrdemServico> filtrar(OrdemServicoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<OrdemServico> criteria = builder.createQuery(OrdemServico.class);
		
		Root<OrdemServico> root = criteria.from(OrdemServico.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<OrdemServico> query = manager.createQuery(criteria);
		adicionarRestricoedePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	@Override
	public Page<ResumOrdemServico> resumo(OrdemServicoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumOrdemServico> criteria = builder.createQuery(ResumOrdemServico.class);
		Root<OrdemServico> root = criteria.from(OrdemServico.class);
		criteria.select(builder.construct(ResumOrdemServico.class, root.get(OrdemServico_.id),
				root.get(OrdemServico_.codigoService), root.get(OrdemServico_.motivoOs).get(MotivoOs_.descricao),
				root.get(OrdemServico_.descricao), root.get(OrdemServico_.prioridadeOs), root.get(OrdemServico_.solicitante),
				root.get(OrdemServico_.cliente).get(Cliente_.id), root.get(OrdemServico_.cliente).get(Cliente_.fantazia),
				root.get(OrdemServico_.dataAbertura)));
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<ResumOrdemServico> query = manager.createQuery(criteria);
		
		adicionarRestricoedePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	

	private Predicate[] criarRestricoes(OrdemServicoFilter filter, CriteriaBuilder builder, Root<OrdemServico> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getCodigoService())) {
			predicates.add(builder.equal(
					root.get(OrdemServico_.codigoService), filter.getCodigoService()));
		}
		
		if(!StringUtils.isEmpty(filter.getCodigoCliente())) {
			Join<OrdemServico, Cliente> joinCliente = root.join(OrdemServico_.cliente);
			predicates.add(builder.equal(
					joinCliente.get(Cliente_.id), filter.getCodigoCliente()));
		}
		
		if(!StringUtils.isEmpty(filter.getMotivoOs())) {
			Join<OrdemServico, MotivoOs> joinMotivoOs = root.join(OrdemServico_.motivoOs);
			predicates.add(builder.equal(
						joinMotivoOs.get(MotivoOs_.id), filter.getMotivoOs()));
		}
		
		if(!StringUtils.isEmpty(filter.getPrioridade())) {
			predicates.add(builder.like(
					builder.lower(root.get(OrdemServico_.prioridadeOs)),
						"%"+filter.getPrioridade().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(filter.getSolicitante())) {
			predicates.add(builder.like(
					builder.lower(root.get(OrdemServico_.solicitante)), 
						"%"+filter.getSolicitante().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(filter.getNomeCliente())) {
			Join<OrdemServico, Cliente> joinCliente = root.join(OrdemServico_.cliente);
			predicates.add(builder.like(
					builder.lower(joinCliente.get(Cliente_.fantazia)),
						"%"+filter.getNomeCliente()+"%"));
		}
		if(!StringUtils.isEmpty(filter.getDataAberturaDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(OrdemServico_.dataAbertura),
					convertToLocalDateTimeViaInstant(filter.getDataAberturaDe())));
		}
		
		if(!StringUtils.isEmpty(filter.getDataAberturaAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(OrdemServico_.dataAbertura),
					convertToLocalDateTimeViaInstant(filter.getDataAberturaAte())));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(OrdemServicoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class); 
		Root<OrdemServico> root = criteria.from(OrdemServico.class);
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
