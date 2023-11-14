package com.sistemaf.infrastructure.services;

import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.definition.FileReference;
import com.sistemaf.domain.service.FileService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@AllArgsConstructor
public class MinioFileService implements FileService {

    private final MinioClient minioClient;
    private final SistemFApiProperty props;
    @Override
    public URL generateUploadSignedUrl(FileReference fileReference) {
        try {
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("response-content-type", fileReference.getContentType());
            String url =  minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                    .builder()
                    .method(Method.PUT)
                    .bucket(props.getBucketName())
                    .object(fileReference.getFileName())
                    .expiry(30, TimeUnit.MINUTES)
                    .extraQueryParams(reqParams)
                    .build());
            return URI.create(url).toURL();
        }catch (Exception e ) {
            log.error("Failed when try generate URL", e.getCause());
            throw new BusinessException("Erro ao gerar URL");
        }
    }
}
