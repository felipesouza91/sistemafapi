package com.sistemaf.domain.service;

import com.sistemaf.domain.model.ClientFile;
import com.sistemaf.domain.model.definition.FileReference;

import java.io.File;
import java.net.URL;

public interface FileService {
    URL generateUploadSignedUrl(FileReference fileReference);

    URL generateDownloadSignedUrl(FileReference clientFile);

    boolean fileExists(FileReference fileReference);
}