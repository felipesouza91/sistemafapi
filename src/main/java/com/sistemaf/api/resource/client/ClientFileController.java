package com.sistemaf.api.resource.client;

import com.sistemaf.api.dto.input.FileInput;
import com.sistemaf.api.dto.manager.FileReferenceMapper;
import com.sistemaf.api.dto.model.UploadFileUrlDTO;
import com.sistemaf.domain.service.ClientFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients/{clientId}/files")
@AllArgsConstructor
public class ClientFileController {

    private final ClientFileService clientFileService;

    private final FileReferenceMapper fileReferenceMapper = FileReferenceMapper.INSTANCE;

    @PutMapping("/upload")
    public ResponseEntity<UploadFileUrlDTO> newUploadSignedUrl(@PathVariable Long clientId, @RequestBody FileInput fileInput) {
       UploadFileUrlDTO fileUrlDTO = clientFileService.generateSignedUrl(clientId,fileReferenceMapper.toModel(fileInput));
       return ResponseEntity.ok(fileUrlDTO);
    }
}
