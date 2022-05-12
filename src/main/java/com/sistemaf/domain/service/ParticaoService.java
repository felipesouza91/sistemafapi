package com.sistemaf.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.exception.EntityUsedException;
import com.sistemaf.domain.model.Particao;
import com.sistemaf.domain.model.custompk.ParticaoPk;
import com.sistemaf.domain.repository.ParticaoRepository;

@Service
public class ParticaoService {

	@Autowired
	private ParticaoRepository particaoDao;
	
	public List<Particao> getAll() {
		return particaoDao.findAll();
	}
	
	public Particao getByID(ParticaoPk id) {
		Particao particao = this.getByIdOptional(id);
		return particao;
	}
	
	public Particao save(Particao particao) {
		if (this.getByIdOptional(particao.getParticaoPk()) != null ) {
			throw new EntityUsedException("A partição já existe");
		}
		return particaoDao.save(particao);
	}
	
	private Particao getByIdOptional(ParticaoPk id) {
		Optional<Particao> partitionOptional = this.particaoDao.findById(id); 
		return partitionOptional.orElseThrow(() -> new EntityNotFoundException("A partição não foi encontrada"));
	}
}
