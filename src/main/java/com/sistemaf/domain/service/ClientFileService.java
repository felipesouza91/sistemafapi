package com.sistemaf.domain.service;

import com.sistemaf.api.dto.model.UploadFileUrlDTO;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.ClientFile;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.definition.FileReference;
import com.sistemaf.domain.repository.cliente.ClientFileRepository;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class ClientFileService {

    private final ClienteRepository clientRepository;
    private final ClientFileRepository clientFileRepository;
    private final FileService fileService;

    @Transactional
    public UploadFileUrlDTO generateSignedUrl(Long clientId, FileReference fileReference) {
        Cliente cliente = clientRepository.findById(clientId).orElseThrow(() -> new BusinessException("O Cliente não foi encontrado"));
        ClientFile clientFile = new ClientFile();
        clientFile.setOriginalFileName(fileReference.getFileName());
        clientFile.setFileName(OffsetDateTime.now().toEpochSecond()+fileReference.getFileName());
        clientFile.setContentType(fileReference.getContentType());
        clientFile.setClient(cliente);
        ClientFile savedClientFile = clientFileRepository.save(clientFile);
        URL signedUrl = fileService.generateUploadSignedUrl(fileReference);
        return UploadFileUrlDTO.builder()
                .fileReferenceId(savedClientFile.getId())
                .uploadUrl(signedUrl)
                .build();
    }
}
