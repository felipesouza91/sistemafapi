package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.ClientUnavailableException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.DvrFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.repository.dvr.DvrRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DvrService {
	
	@Autowired
	private DvrRepository dvrRepository;	
	
	@Autowired
	private ClienteService clienteService;

	public Page<Dvr> filtrar(DvrFilter dvrFilter, Pageable pageable) {
		return dvrRepository.filtrar(dvrFilter, pageable);
	}

	public Dvr buscarPorCodigo(Long codigo) {
		return getDvrOption(codigo);
	}

	public Dvr salvar( Dvr dvr) {
		Cliente cliente = clienteService.listarPorCodigo(dvr.getCliente().getId());
		if(!cliente.getAtivo()) {
			throw new ClientUnavailableException("O Cliente selecionado esta desativado");
		}	
		return dvrRepository.save(dvr);
	}

	public Dvr atualizar(Long codigo, Dvr dvr) {
		Dvr dvrSalvo = getDvrOption(codigo);
		BeanUtils.copyProperties(dvr, dvrSalvo, "id");
		return salvar(dvrSalvo);
	}

	public void remover(Long codigo) {
		dvrRepository.deleteById(codigo);
	}
	
	private Dvr getDvrOption(Long codigo) {
		Optional<Dvr> dvrOptional = dvrRepository.findById(codigo);
	
		return dvrOptional.orElseThrow(() -> new EntityNotFoundException("O DVR não existe"));
	}
}
