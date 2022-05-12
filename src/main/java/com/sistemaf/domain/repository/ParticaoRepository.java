package com.sistemaf.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistemaf.domain.model.Particao;
import com.sistemaf.domain.model.custompk.ParticaoPk;

public interface ParticaoRepository extends JpaRepository<Particao, ParticaoPk>{

	@Query("select max(p.index) from Particao p")
	public Long getMaxIndex();
	
	public Particao findByIndex(Long id);
}
	