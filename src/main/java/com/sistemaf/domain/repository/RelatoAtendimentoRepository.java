package com.sistemaf.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.RelatoAtendimento;

public interface RelatoAtendimentoRepository extends JpaRepository<RelatoAtendimento, Long>{

	public List<RelatoAtendimento> findByAtendimentoId(Long id);
}
