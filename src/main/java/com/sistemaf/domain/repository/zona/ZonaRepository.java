package com.sistemaf.domain.repository.zona;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Zona;
import com.sistemaf.domain.model.custompk.ZonaPk;

public interface ZonaRepository extends JpaRepository<Zona, ZonaPk>{

}
