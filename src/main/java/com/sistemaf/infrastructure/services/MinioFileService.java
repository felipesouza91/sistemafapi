package com.sistemaf.infrastructure.services;

import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.FileServiceException;
import com.sistemaf.domain.model.definition.FileReference;
import com.sistemaf.domain.service.FileService;
import io.minio.*;
import io.minio.errors.*;
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
            log.error("Failed when try generate upload URL", e.getCause());
            throw new BusinessException("Erro ao gerar URL");
        }
    }

    @Override
    public URL generateDownloadSignedUrl(FileReference fileReference) {
        this.fileExists(fileReference);
        try {
            String downloadUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                    .builder()
                    .method(Method.GET)
                            .bucket(props.getBucketName())
                            .object(fileReference.getFileName())
                            .expiry(30, TimeUnit.MINUTES)
                    .build());
            return URI.create(downloadUrl).toURL();
        } catch (Exception e) {
            log.error("Failed when try generate download URL", e.getCause());
            throw new BusinessException("Erro ao gerar URL para download");
        }
    }

    @Override
    public boolean fileExists(FileReference fileReference)  {
        try {
            StatObjectResponse objectStat =
                    minioClient.statObject(
                            StatObjectArgs.builder().bucket(props.getBucketName()).object(fileReference.getFileName()).build());
           return objectStat != null;
        } catch (ErrorResponseException errorResponseException  ) {
            log.error("File does not exists: " + fileReference.getFileName(), errorResponseException.getCause());
           return false;
        }
        catch (Exception e) {
            log.error("File does not exists: " + fileReference.getFileName(), e.getCause());
            throw new FileServiceException("Arquivo não foi encontrado");
        }

    }

    @Override
    public void deleteFile(FileReference fileReference) {
        try {
            this.minioClient.removeObject(RemoveObjectArgs.builder()
                            .bucket(this.props.getBucketName())
                            .object(fileReference.getFileName())
                    .build());
        }catch (Exception e) {
            throw new FileServiceException("Error to remove file", e.getCause());
        }
    }
}
