package com.sistemaf.infrastructure.services;

import com.sistemaf.domain.model.definition.FileReference;
import com.sistemaf.domain.service.FileService;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class MinioFileService implements FileService {
    @Override
    public URL generateUploadSignedUrl(FileReference fileReference) {
        return null;
    }
}
