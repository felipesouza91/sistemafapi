package com.sistemaf.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Contato;
import com.sistemaf.domain.repository.contato.ContatoRepository;


@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoDao;
	
	public Contato findById(Long id) {
		Contato contato = this.unwrapContatoOptiona(id);
		return contato;
	}
	
	public Contato save(Contato contato) {
		return this.contatoDao.save(contato);
	}
	
	public Contato update(Long id, Contato contato) {
		Contato contatoSalvo = this.findById(id);
		BeanUtils.copyProperties(contato, contatoSalvo, "id, particao");
		return this.save(contatoSalvo);
	}
	
	public void delete(Long id) {
		this.contatoDao.deleteById(id);
	}
	
	private Contato unwrapContatoOptiona(Long id) {
		Optional<Contato> contactOptional = this.contatoDao.findById(id);
		return contactOptional.orElseThrow(() -> new EntityNotFoundException("O contato não existe"));
 	}
}
