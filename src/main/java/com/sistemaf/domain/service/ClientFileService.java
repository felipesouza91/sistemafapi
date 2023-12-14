package com.sistemaf.domain.service;

import com.sistemaf.api.dto.model.UploadFileUrlResponse;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientFileService {

    private final ClienteRepository clientRepository;
    private final ClientFileRepository clientFileRepository;
    private final FileService fileService;

    @Transactional
    public UploadFileUrlResponse generateUploadData(Long clientId, FileReference fileReference) {
        Cliente cliente = clientRepository.findById(clientId).orElseThrow(() -> new BusinessException("O Cliente não foi encontrado"));
        ClientFile clientFile = new ClientFile();
        clientFile.setOriginalFileName(fileReference.getFileName());
        String fileName = OffsetDateTime.now().toEpochSecond()+fileReference.getFileName();
        clientFile.setFileName(fileName);
        fileReference.setFileName(fileName);
        clientFile.setContentType(fileReference.getContentType());
        clientFile.setClient(cliente);
        ClientFile savedClientFile = clientFileRepository.save(clientFile);
        URL signedUrl = fileService.generateUploadSignedUrl(fileReference);
        return UploadFileUrlResponse.builder()
                .fileReferenceId(savedClientFile.getId())
                .uploadUrl(signedUrl)
                .build();
    }

    public URL generateDownloadSignedUrl(Long clientId, UUID fileId) {
        Cliente cliente = clientRepository.findById(clientId).orElseThrow(() -> new BusinessException("O Cliente não foi encontrado"));
        ClientFile clientFile =  clientFileRepository.findById(fileId).orElseThrow(() -> new EntityNotFoundException("O Arquivo não foi encontrado"));
        URL url = fileService.generateDownloadSignedUrl(clientFile);
        return url;
    }
}
