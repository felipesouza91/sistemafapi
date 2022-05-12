package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.BairroFilter;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.repository.bairro.BairroRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BairroService {

	@Autowired
	private CidadeService cityService;

	@Autowired
	private BairroRepository bairroRepository;

	public Page<Bairro> pesquisar(BairroFilter bairroFilter, Pageable pageable) {
		return bairroRepository.filtrar(bairroFilter, pageable);
	}

	public Bairro buscarPorCodigo(Long codigo) {
		return this.getBairroOptional(codigo);
	}

	public Bairro salvar(Bairro bairro) {
		try {
			this.cityService.buscaPorCodigo(bairro.getCidade().getId());
			if(this.bairroRepository.findByCidadeAndNome(bairro.getCidade(), bairro.getNome()).isPresent()) {
				throw new BusinessException("O Bairro já existe");
			}
			return bairroRepository.save(bairro);
		}catch (EntityNotFoundException e ) {
			throw new BusinessException(e.getMessage());
		}
	}

	public Bairro atualizar(Long codigo, Bairro bairro) {
		try {
			this.cityService.buscaPorCodigo(bairro.getCidade().getId());
		}catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		Bairro salvo = this.getBairroOptional(codigo);
		BeanUtils.copyProperties(bairro, salvo, "id");
		return bairroRepository.save(salvo);
	}

	public void deletar(Long codigo) {
		this.getBairroOptional(codigo);
		bairroRepository.deleteById(codigo);
	}

	private Bairro buscarBairroPorCidadeENome(Cidade cidade, String nome) {
		Optional<Bairro> neighborhoodOptional = bairroRepository.findByCidadeAndNome(cidade, nome);
		return neighborhoodOptional.orElseThrow(() -> new EntityNotFoundException("O Bairro não foi encontrado ou não existe")) ;
	}

	private Bairro getBairroOptional(Long codigo) {
		Optional<Bairro> neighborhoodOptional = bairroRepository.findById(codigo);
		return neighborhoodOptional.orElseThrow(() -> new EntityNotFoundException("O Bairro não foi encontrado ou não existe")) ;
	}

}
