package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.OrdemServicoFilter;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.projection.ResumOrdemServico;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import com.sistemaf.domain.repository.motivoos.MotivoOsRepository;
import com.sistemaf.domain.repository.ordemservico.OrdemServicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private MotivoOsRepository motivoOsRepository;

	public Page<OrdemServico> filtrar(OrdemServicoFilter filter, Pageable pageable) {
		return ordemServicoRepository.filtrar(filter, pageable);
	}

	public Page<ResumOrdemServico> resumir(OrdemServicoFilter filter, Pageable pageable) {
		return ordemServicoRepository.resumo(filter, pageable);
	}

	public OrdemServico listaPorCodigo(Long codigo) {
		return getOrdemServicoOptional(codigo);
	}

	public OrdemServico salvar( OrdemServico ordemServico) {
		this.clienteRepository.findById(ordemServico.getCliente().getId())
						.orElseThrow(() -> new BusinessException("O Cliente não existe"));
		this.motivoOsRepository.findById(ordemServico.getMotivoOs().getId()).orElseThrow(() -> new BusinessException("O Motivo da Ordem não existe"));
		return ordemServicoRepository.save(ordemServico);
	}

	public OrdemServico atualizar(Long codigo, OrdemServico ordemServico) {
		OrdemServico osSalva = getOrdemServicoOptional(codigo);
		this.clienteRepository.findById(ordemServico.getCliente().getId())
						.orElseThrow(() -> new BusinessException("O Cliente não existe"));
		this.motivoOsRepository.findById(ordemServico.getMotivoOs().getId()).orElseThrow(() -> new BusinessException("O Motivo da Ordem não existe"));
		if(osSalva.getFechado() != null &&  osSalva.getFechado()) {
			throw  new BusinessException("A ordem já encontra-se fechada, não é possivel atualizar");
		}
		BeanUtils.copyProperties(ordemServico, osSalva, "id", "cliente", "dataAbertura");
		return ordemServicoRepository.save(osSalva);
	}

	public void remover(Long codigo) {
		this.getOrdemServicoOptional(codigo);
		ordemServicoRepository.deleteById(codigo);
	}

	private OrdemServico getOrdemServicoOptional(Long codigo) {
		Optional<OrdemServico> osOptional = ordemServicoRepository.findById(codigo);
		return osOptional.orElseThrow(()-> new EntityNotFoundException("A ordem de serviço não existe"));
	}

}
