package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.FechamentoOsFilter;
import com.sistemaf.domain.model.FechamentoOs;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.repository.fechamentoos.FechamentoOsRepository;
import com.sistemaf.domain.repository.ordemservico.OrdemServicoRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
		OrdemServico serviceOrder =
						serviceOrderRepository.findById(fechamentoOs.getOs().getId())
										.orElseThrow(() -> new BusinessException("A ordem de serviço não existe!") );

		if(BooleanUtils.isTrue(serviceOrder.getFechado())) {
			throw new BusinessException("A ordem de serviço já está fechada");
		}
		FechamentoOs closedOrder = fechamentoOsReposioty.save(fechamentoOs);
		serviceOrder.setFechado(true);
		return closedOrder;
	}


	public void remover(Long codigo) {
		FechamentoOs fechamentoOs = this.getFechamentoOptional(codigo);
		OrdemServico ordemServico =
						serviceOrderRepository.findById(fechamentoOs.getId())
										.orElseThrow(() -> new BusinessException("A ordem de serviço não foi encontrada"));
		ordemServico.setFechado(false);
		try {
			fechamentoOsReposioty.deleteById(codigo);
			serviceOrderRepository.save(ordemServico);
		}catch (DataIntegrityViolationException e) {
			throw  new BusinessException("Erro ao excluir fechamento de ordem de serviço");
		}
	}

	private FechamentoOs getFechamentoOptional(Long codigo) {
		Optional<FechamentoOs> closedOptional = fechamentoOsReposioty.findById(codigo);
		return closedOptional.orElseThrow(() -> new EntityNotFoundException("O registro de fechamento da ordem solicitada não existe"));
	}
}
