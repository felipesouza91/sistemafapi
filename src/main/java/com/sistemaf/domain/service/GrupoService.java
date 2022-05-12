package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.GrupoFilter;
import com.sistemaf.domain.model.Grupo;
import com.sistemaf.domain.repository.grupo.GrupoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable){
		return grupoRepository.filtrar(grupoFilter, pageable);
	}
	
	public Grupo listaPorCodigo(Long codigo) {
		return this.getGrupoOptional(codigo);
	}
	
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	public void excluir(Long codigo) {
		this.getGrupoOptional(codigo);
		grupoRepository.deleteById(codigo);
	}

	public Grupo atualizar(Long codigo,Grupo grupo) {
		Grupo grupoSalvo = this.getGrupoOptional(codigo);
		BeanUtils.copyProperties(grupo, grupoSalvo, "id");
		return grupoRepository.save(grupoSalvo);
	}
	
	private Grupo getGrupoOptional(Long codigo) {
		Optional<Grupo> groupOptional = grupoRepository.findById(codigo);
	
		return groupOptional.orElseThrow(()-> new EntityNotFoundException("O Grupo solicitado não existe"));
	}


}
