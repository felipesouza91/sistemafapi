package com.sistemaf.core.files;


import com.sistemaf.core.SistemFApiProperty;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@Configuration
public class FilesConfig {

    @Bean
    public MinioClient createMinioClientBean(SistemFApiProperty props) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(props.getMinio().getServerEndPoint())
                .credentials(props.getMinio().getAccessKey(), props.getMinio().getSecretKey())
                .build();
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(props.getBucketName()).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(props.getBucketName()).build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }

        } catch (Exception e) {
            log.error("Error when try create a bucket:", e);
        }
        log.debug("Bucket bean create with successful ");
        return minioClient;
    }
}
