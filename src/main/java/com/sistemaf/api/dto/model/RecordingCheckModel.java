package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.model.resume.DvrResumeModel;
import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(name = "Recorging Check Model")
public class RecordingCheckModel {

    @Schema(description =  "Recording check code", example = "1")
    private Long id;

    @Schema(description =  "Status", example = "ONLINE")
    private String status;

    @Schema(description =  "HD Total Storage", example = "500GB")
    private String hd;

    @Schema(description =  "Recording day total", example = "100")
    private int qtdGravacao;

    @Schema(description =  "Check create date", example = "2020-12-20 12:00:00")
    private OffsetDateTime dataTeste;

    private DvrResumeModel dvr;

    private UserResumeModel usuario;
}
