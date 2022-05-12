package com.sistemaf.api.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemaf.api.dto.model.resume.DvrResumeModel;
import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Recorging Check Model")
public class RecordingCheckModel {

    @ApiModelProperty(value = "Recording check code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Status", example = "ONLINE")
    private String status;

    @ApiModelProperty(value = "HD Total Storage", example = "500GB")
    private String hd;

    @ApiModelProperty(value = "Recording day total", example = "100")
    private int qtdGravacao;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "Check create date", example = "2020-12-20 12:00:00")
    private LocalDateTime dataTeste;

    private DvrResumeModel dvr;

    private UserResumeModel usuario;
}
