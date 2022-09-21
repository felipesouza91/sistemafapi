package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(name = "Recording Check Resume Model")
public class RecordingCheckResumeModel {

    @Schema(description ="Recording check code", example = "1")
    private Long id;

    @Schema(description ="Status", example = "ONLINE")
    private String status;

    @Schema(description ="HD Total Storage", example = "500GB")
    private String hd;

    @Schema(description ="Recording day total", example = "100")
    private int qtdGravacao;

    @Schema(description ="Check create date", example = "2020-12-20 12:00:00")
    private OffsetDateTime dataTeste;

    @Schema(description ="Dvr code", example = "1")
    private Long idDvr;

    @Schema(description ="Manufacturer name", example = "Any manufacturer")
    private String fabricante;

    @Schema(description ="Model name", example = "Any model name")
    private String modeloDVr;

    @Schema(description ="User name", example = "John")
    private String nomeUsuario;

    @Schema(description ="Client code", example = "John")
    private Long idCliente;

    @Schema(description ="Client name", example = "Client name")
    private String nomeFantazia;
}
