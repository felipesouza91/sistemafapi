package com.sistemaf.domain.service;

import java.util.Optional;

import javax.validation.Valid;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.ClienteFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.repository.bairro.BairroRepository;
import com.sistemaf.domain.repository.cliente.ClienteRepository;

import com.sistemaf.domain.repository.grupo.GrupoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private GrupoRepository grupoRepository;
	
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		return clienteRepository.filtrar(clienteFilter, pageable);
	}
	
	public Cliente listarPorCodigo(Long codigo) {
		return this.getClienteOptional(codigo);
	}

	public Cliente salvar( Cliente cliente) {
		this.verifyNeighborhood(cliente.getEndereco().getBairro().getId());
		this.verifyGroup(cliente.getGrupo().getId());
		Optional<Cliente> partitionCodeExists = this.clienteRepository.findByCodigoParticao(cliente.getCodigoParticao());
		if(partitionCodeExists.isPresent()) {
			throw new BusinessException("Já existe cliente com partição solicitada");
		}
		return clienteRepository.save(cliente);
	}


	public Cliente atualizar(Long codigo, @Valid Cliente cliente) {
		Cliente clienteSalvo = listarPorCodigo(codigo);
		this.verifyNeighborhood(cliente.getEndereco().getBairro().getId());
		this.verifyGroup(cliente.getGrupo().getId());
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		Optional<Cliente> partitionCodeExists = this.clienteRepository.findByCodigoParticao(cliente.getCodigoParticao());
		if(partitionCodeExists.isPresent() && partitionCodeExists.get().getId() != clienteSalvo.getId()) {
			throw new BusinessException("Já existe cliente com partição solicitada");
		}
		return clienteRepository.save(clienteSalvo);
	}
	
	public void deleteById(Long codigo) {
		this.getClienteOptional(codigo);
		try {
			clienteRepository.deleteById(codigo);
		} catch (Exception e ) {
			throw new BusinessException("Erro ao realizar exclusão de cliente");
		}
	}

	private Cliente getClienteOptional(Long codigo) {
		Optional<Cliente> clientOptional = clienteRepository.findById(codigo);
		return clientOptional.orElseThrow(()-> new EntityNotFoundException("O Cliente encontrado ou não existe"));
	}

	private void verifyNeighborhood(Long  neighborhoodCode) {
		this.bairroRepository.findById(neighborhoodCode).orElseThrow(() -> new BusinessException("O Bairro inserido não existe"));
	}

	private void verifyGroup(Long  groupCode) {
		this.grupoRepository.findById(groupCode).orElseThrow(() -> new BusinessException("O Grupo inserido não existe"));
	}
}
