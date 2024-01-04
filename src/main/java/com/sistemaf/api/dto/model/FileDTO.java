package com.sistemaf.api.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FileDTO {

    private UUID id;

    private String fileName;

    private String contentType;

    private URL fileUrl;
}
