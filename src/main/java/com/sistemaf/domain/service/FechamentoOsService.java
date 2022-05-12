package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.repository.ordemservico.OrdemServicoRepository;
import org.apache.commons.lang3.BooleanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.FechamentoOsFilter;
import com.sistemaf.domain.model.FechamentoOs;
import com.sistemaf.domain.repository.fechamentoos.FechamentoOsRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FechamentoOsService {
	
	@Autowired
	private FechamentoOsRepository fechamentoOsReposioty;

	@Autowired
	private OrdemServicoRepository serviceOrderRepository;

	public Page<FechamentoOs> filtrar(FechamentoOsFilter filter, Pageable pageable) {
		return fechamentoOsReposioty.filtrar(filter, pageable);
	}

	public FechamentoOs listarPorCodigo(Long codigo) {
		return getFechamentoOptional(codigo);
	}

	@Transactional
	public FechamentoOs salvar(FechamentoOs fechamentoOs) {
		Optional<OrdemServico> serviceOrder = serviceOrderRepository.findById(fechamentoOs.getOs().getId());
		if(serviceOrder.isEmpty()) {
			throw new BusinessException("A ordem de serviço não existe!");
		}
		if(BooleanUtils.isTrue(serviceOrder.get().getFechado())) {
			throw new BusinessException("A ordem de serviço já está fechada");
		}
		FechamentoOs closedOrder = fechamentoOsReposioty.save(fechamentoOs);
		serviceOrder.get().setFechado(true);
		return closedOrder;
	}

	public FechamentoOs atualizar(Long codigo, FechamentoOs fechamentoOs) {
		FechamentoOs fechamentoOsSalvo = getFechamentoOptional(codigo);
		BeanUtils.copyProperties(fechamentoOs, fechamentoOsSalvo, "id", "os", "dataFechamento");
		return salvar(fechamentoOsSalvo);
	}

	public void remover(Long codigo) {
		this.getFechamentoOptional(codigo);
		try {
			fechamentoOsReposioty.deleteById(codigo);
		}catch (DataIntegrityViolationException e) {
			throw  new BusinessException("Erro ao excluir fechamento de ordem de serviço");
		}
	}

	private FechamentoOs getFechamentoOptional(Long codigo) {
		Optional<FechamentoOs> closedOptional = fechamentoOsReposioty.findById(codigo);
		return closedOptional.orElseThrow(() -> new EntityNotFoundException("O registro de fechamento da ordem solicitada não existe"));
	}
}
