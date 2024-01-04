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
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class ClientFileService {

    private final ClienteRepository clientRepository;
    private final ClientFileRepository clientFileRepository;
    private final FileService fileService;

    public List<ClientFile> getClientsFileByClientId(Long clientId) {
        return this.clientFileRepository.findAllByClientId(clientId);
    }

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

    @Transactional
    public void removeOldTempFiles() {
        log.info("Run scheduled to check file is temp");
        List<ClientFile> tempFileList =   this.clientFileRepository.findAllByTempIsTrue();
        tempFileList.forEach(file -> {
          if(this.fileService.fileExists(file)) {
              file.setTemp(false);
              clientFileRepository.save(file);

          } else {
              clientFileRepository.delete(file);
          }
          this.clientFileRepository.flush();
      });
    }
}
