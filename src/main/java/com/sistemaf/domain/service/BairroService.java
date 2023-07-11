package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.BairroFilter;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.repository.bairro.BairroRepository;
import com.sistemaf.domain.repository.cidade.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BairroService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private BairroRepository bairroRepository;

	public Page<Bairro> pesquisar(BairroFilter bairroFilter, Pageable pageable) {
		return bairroRepository.filtrar(bairroFilter, pageable);
	}

	public Bairro buscarPorCodigo(Long codigo) {
		return this.getBairroOptional(codigo);
	}

	public Bairro salvar(Bairro bairro) {
		this.cidadeRepository.findById(bairro.getCidade().getId())
				.orElseThrow(() -> new BusinessException("Cidade invalida"));
		Optional<Bairro> bairroExists = this.bairroRepository.findByCidadeAndNome(bairro.getCidade(), bairro.getNome());
		if (bairroExists.isPresent()) {
			throw new BusinessException("O Bairro já existe");
		}
		return bairroRepository.save(bairro);
	}

	public Bairro atualizar(Long codigo, Bairro bairro) {
		Cidade updatedCidade = this.cidadeRepository.findById(bairro.getCidade().getId())
				.orElseThrow(() -> new BusinessException("Cidade invalida"));
		Bairro savedBairro = this.getBairroOptional(codigo);
		boolean isNeighborhoodExistsInCity =
						this.bairroRepository.findByCidadeAndNome(bairro.getCidade(), bairro.getNome()).isPresent();
		if(isNeighborhoodExistsInCity) {
			throw new BusinessException("O Bairro já existe na cidade selecionada");
		}
		BeanUtils.copyProperties(bairro, savedBairro, "id");
		return bairroRepository.save(savedBairro);
	}

	public void deletar(Long codigo) {
		this.getBairroOptional(codigo);
		bairroRepository.deleteById(codigo);
	}

	private Bairro getBairroOptional(Long codigo) {
		Optional<Bairro> neighborhoodOptional = bairroRepository.findById(codigo);
		return neighborhoodOptional
				.orElseThrow(() -> new EntityNotFoundException("O Bairro não foi encontrado ou não existe"));
	}

}
