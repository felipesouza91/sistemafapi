package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.CidadeFilter;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.repository.cidade.CidadeRepository;


@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable){
		return cidadeRepository.filtrar(cidadeFilter, pageable);
	}
	
	public Cidade buscaPorCodigo(Long codigo) {
		return this.getCidade(codigo);
	}
	
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}


	public Cidade atualizar(Long codigo,Cidade cidade) {
		Cidade cidadeSalva = this.getCidade(codigo);
		BeanUtils.copyProperties(cidade, cidadeSalva,"id");
		return cidadeRepository.save(cidadeSalva);
	}

	public void deletar(Long codigo) {
		this.getCidade(codigo);
		try {
			cidadeRepository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("Não foi possivel excluir a cidades solicitada");
		}

	}

	private Cidade getCidade(Long codigo) {
		Optional<Cidade> cityOptional = cidadeRepository.findById(codigo);
		return cityOptional.orElseThrow(
			() ->  new EntityNotFoundException("A cidade buscado não foi encontrada ou não existe"));
	}

}
