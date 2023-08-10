package com.sistemaf.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.MotivoOsFilter;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.repository.motivoos.MotivoOsRepository;

@Service
public class MotivoOsService {

	@Autowired
	private MotivoOsRepository motivoOsRepository;

	public Page<MotivoOs> filtrar(MotivoOsFilter filter, Pageable pageable) {
		return motivoOsRepository.filtrar(filter, pageable);
	}

	public MotivoOs listarPorCodigo(Long codigo) {
		return getMotivoOsOption(codigo);
	}

	public MotivoOs salvar(MotivoOs motivoOs) {
		return motivoOsRepository.save(motivoOs);
	}

	public MotivoOs atualizar(Long codigo, MotivoOs motivoOs) {
		MotivoOs motivoOsSalvo = getMotivoOsOption(codigo);
		BeanUtils.copyProperties(motivoOs, motivoOsSalvo, "id");
		return salvar(motivoOsSalvo);
	}

	public void remover(Long codigo) {
		this.getMotivoOsOption(codigo);
		motivoOsRepository.deleteById(codigo);
	}

	private MotivoOs getMotivoOsOption(Long codigo) {
		Optional<MotivoOs> oSCauseOptional = motivoOsRepository.findById(codigo);
		return oSCauseOptional.orElseThrow(()-> new EntityNotFoundException("O Grupo solicitado não existe"));
	}

}
