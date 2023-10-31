package com.sistemaf.api.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UploadFileUrlDTO {

    private UUID fileReferenceId;
    private URL uploadUrl;
}
