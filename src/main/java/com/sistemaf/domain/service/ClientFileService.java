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
public class ClientFileService implements  RemoveOldFilesService{

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

    @Override
    @Transactional
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24, initialDelay = 1000)
    public void removeOldTempFiles() {
        log.info("Run scheduled to check file is temp start");
        List<ClientFile> tempFileList =   this.clientFileRepository.findAllByTempIsTrue();
        tempFileList.forEach(file -> {
            log.info("Check if file {} is temporary", file.getFileName());
            if(this.fileService.fileExists(file)) {
              log.info("File exits in Bucket. Update file {} to definitive", file.getFileName());
              file.setTemp(false);
              clientFileRepository.save(file);
            } else {
              log.info("File does not exits in Bucket. Remove file {}", file.getFileName());
              clientFileRepository.delete(file);
            }
            this.clientFileRepository.flush();
      });
        log.info("Run scheduled to check file is temp finish");
    }

    @Transactional
    public void deleteByFileId(UUID fileId) {
        ClientFile clientFile = this.clientFileRepository.findById(fileId).orElseThrow(() ->  new EntityNotFoundException("File not found"));
        this.clientFileRepository.deleteById(clientFile.getId());
        this.fileService.deleteFile(clientFile);
    }
}
