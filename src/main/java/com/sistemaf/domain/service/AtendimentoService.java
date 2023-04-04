package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.AtendimentoFilter;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.projection.ResumoAtendimento;
import com.sistemaf.domain.repository.atendimento.AtendimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	public Page<Atendimento> listar(AtendimentoFilter filter, Pageable page){
		return atendimentoRepository.filtrar(filter, page);
	}
	
	public Page<ResumoAtendimento> resumo(AtendimentoFilter filter, Pageable pageable) {
		return atendimentoRepository.resumo(filter, pageable);
	}
	
	public Atendimento buscarPorCodigo(Long codigo) {
		return buscarPorCodigoOptional(codigo);
	}
	
	public Atendimento salvar(Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	}
	
	public Atendimento atualizar(Long codigo, Atendimento atendimento) {
		Atendimento salvo = this.buscarPorCodigoOptional(codigo);
		BeanUtils.copyProperties(atendimento, salvo, "id", "cliente");
		return atendimentoRepository.save(salvo);
	}

	private Atendimento buscarPorCodigoOptional(Long codigo) {
		Optional<Atendimento> attendanceOptional = atendimentoRepository.findById(codigo);
		return attendanceOptional.orElseThrow(() -> new EntityNotFoundException("Atendimento não encontrada ou não existe"));
	}
}
