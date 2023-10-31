package com.sistemaf.api.dto.input;

import com.sistemaf.domain.validations.AllowedContentTypes;
import com.sistemaf.domain.validations.AllowedFileExtensions;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class FileInput {

    @NotBlank
    private String fileName;

    @NotBlank
    @AllowedContentTypes({MediaType.APPLICATION_PDF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private String contentType;

}
