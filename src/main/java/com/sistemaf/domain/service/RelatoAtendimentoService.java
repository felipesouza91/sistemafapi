package com.sistemaf.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.model.RelatoAtendimento;
import com.sistemaf.domain.repository.RelatoAtendimentoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatoAtendimentoService {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private RelatoAtendimentoRepository relatoRepository;
	
	public RelatoAtendimento buscarPorCodigo(Long id ) {
		Optional<RelatoAtendimento> reportServiceOptional = this.relatoRepository.findById(id);
		return reportServiceOptional.orElseThrow(()-> new EntityNotFoundException("O relato solicitado não existe"));
	}
	
	public List<RelatoAtendimento> findAtendimento(Long idAtendimento) {
		atendimentoService.buscarPorCodigo(idAtendimento);
		return relatoRepository.findByAtendimentoId(idAtendimento);
	}

	public RelatoAtendimento salvar(Long idAtendimento, @Valid RelatoAtendimento relato) {
		Atendimento atendimento = atendimentoService.buscarPorCodigo(idAtendimento);
		relato.setAtendimento(atendimento);
		return relatoRepository.save(relato);
	}

	public RelatoAtendimento atualizar(Long codigo, @Valid RelatoAtendimento relato) {
		RelatoAtendimento relatoSalvo = this.buscarPorCodigo(codigo);
		BeanUtils.copyProperties(relato, relatoSalvo, "id","usuario","dataCriacao","atendimento");
		return relatoRepository.save(relatoSalvo);
	}
	
	
}
