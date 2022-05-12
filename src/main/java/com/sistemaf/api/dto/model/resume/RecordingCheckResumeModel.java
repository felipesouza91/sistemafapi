package com.sistemaf.api.dto.model.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Recording Check Resume Model")
public class RecordingCheckResumeModel {

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

    @ApiModelProperty(value = "Dvr code", example = "1")
    private Long idDvr;

    @ApiModelProperty(value = "Manufacturer name", example = "Any manufacturer")
    private String fabricante;

    @ApiModelProperty(value = "Model name", example = "Any model name")
    private String modeloDVr;

    @ApiModelProperty(value = "User name", example = "John")
    private String nomeUsuario;

    @ApiModelProperty(value = "Client code", example = "John")
    private Long idCliente;

    @ApiModelProperty(value = "Client name", example = "Client name")
    private String nomeFantazia;
}
