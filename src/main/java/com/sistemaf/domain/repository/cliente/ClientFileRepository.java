package com.sistemaf.domain.repository.cliente;

import com.sistemaf.domain.model.ClientFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientFileRepository extends JpaRepository<ClientFile, UUID> {
    List<ClientFile> findAllByTempIsTrue();

    List<ClientFile> findAllByClientId(Long clientId);
}
