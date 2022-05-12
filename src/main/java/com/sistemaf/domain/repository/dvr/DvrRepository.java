package com.sistemaf.domain.repository.dvr;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Dvr;

public interface DvrRepository extends JpaRepository<Dvr, Long>, DvrRepositoryQuery{

}
