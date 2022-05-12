package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.domain.repository.estoque.fabricante.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabDao;
	
	public Page<Fabricante> getAll(String nome, Pageable page) {
		if(!StringUtils.isEmpty(nome)) {
			return this.fabDao.findByDescricaoIgnoreCaseContaining(nome, page);		
		}
		return this.fabDao.findAll(page);	
	}
	
	public Fabricante getById(Long id) {
		return this.getByIdOptional(id);
	}
	
	public Fabricante save(Fabricante fab) {
		return this.fabDao.save(fab);
	}
	
	public Fabricante update(Long id, Fabricante fab) {
		Fabricante fabSalvo = this.getByIdOptional(id);
		BeanUtils.copyProperties(fab, fabSalvo, "id");
		return this.save(fabSalvo);
	}
	
	public void remove(Long id) {
		this.getByIdOptional(id);
		try {
			this.fabDao.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new BusinessException("Erro ao excluir fabricante");
		}

	}
	
	private Fabricante getByIdOptional(Long id ) {
		Optional<Fabricante> fabOption = fabDao.findById(id);
		return fabOption.orElseThrow(() -> new EntityNotFoundException("Fabricante não encontrado"));
	}
	
}
