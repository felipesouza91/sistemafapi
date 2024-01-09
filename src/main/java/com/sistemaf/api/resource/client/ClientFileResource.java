package com.sistemaf.api.resource.client;

import com.sistemaf.api.dto.input.FileInput;
import com.sistemaf.api.dto.manager.FileReferenceMapper;
import com.sistemaf.api.dto.model.FileDTO;
import com.sistemaf.api.dto.model.UploadFileUrlResponse;
import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.domain.model.ClientFile;
import com.sistemaf.domain.service.ClientFileService;
import com.sistemaf.domain.service.RemoveOldFilesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/clients/{clientId}/files")
@AllArgsConstructor
public class ClientFileResource  {

    private final ClientFileService clientFileService;
    private final SistemFApiProperty sistemFApiProperty;

    private final FileReferenceMapper fileReferenceMapper = FileReferenceMapper.INSTANCE;

    @GetMapping
    public List<FileDTO> getFiles (@PathVariable Long clientId){
        List<ClientFile> files = this.clientFileService.getClientsFileByClientId(clientId);
        List<FileDTO> list = files.stream()
                .map(item -> {
                    try {
                        return FileDTO.builder()
                                .fileName(item.getOriginalFileName())
                                .id(item.getId())
                                .contentType(item.getContentType())
                                .fileUrl(
                                        URI.create(sistemFApiProperty.getApiUrl() + "/clients/" + clientId + "/files/" + item.getId()).toURL())
                                .build();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return list;
    }
    @GetMapping("/{fileId}")
    public ResponseEntity<Void> generateSignedDownloadUrl(@PathVariable Long clientId, @PathVariable UUID fileId) {
        URL downloadUrl = clientFileService.generateDownloadSignedUrl(clientId, fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", downloadUrl.toString());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    @PostMapping("/upload")
    public ResponseEntity<UploadFileUrlResponse> newUploadSignedUrl(@PathVariable Long clientId, @RequestBody FileInput fileInput) {
       UploadFileUrlResponse fileUrlDTO = clientFileService.generateUploadData(clientId,fileReferenceMapper.toModel(fileInput));
       return ResponseEntity.ok(fileUrlDTO);
    }

    @DeleteMapping("/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable UUID fileId) {
        this.clientFileService.deleteByFileId(fileId);
    }


}
