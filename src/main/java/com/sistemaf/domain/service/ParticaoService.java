package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Particao;
import com.sistemaf.domain.model.custompk.ParticaoPk;
import com.sistemaf.domain.repository.ParticaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticaoService {

	@Autowired
	private ParticaoRepository particaoRepository;
	
	public List<Particao> getAll() {
		return particaoRepository.findAll();
	}
	
	public Particao getByID(ParticaoPk id) {
		Particao particao = this.getByIdOptional(id);
		return particao;
	}

	public Particao save(Particao particao) {
		boolean existsPartition = this.particaoRepository.findById(particao.getParticaoPk())
						.isPresent();
		if (existsPartition) {
			throw new BusinessException("A partição já existe");
		}
		return particaoRepository.save(particao);
	}
	
	private Particao getByIdOptional(ParticaoPk id) {
		Optional<Particao> partitionOptional = this.particaoRepository.findById(id);
		return partitionOptional.orElseThrow(() -> new EntityNotFoundException("A partição não foi encontrada"));
	}
}
