package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.model.resume.ClientResumeModel;
import com.sistemaf.domain.model.Dvr;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(name = "Service Order Model")
public class ServiceOrderModel {

    @Schema(description =  "Service order code", example = "1")
    private Long id;

    @Schema(description =  "Service code (integration code Inside Sistemas)", example = "1555")
    private Integer codigoService;

    @Schema(description =  "Service code (integration code Sigma)", example = "1555")
    private Integer codigoSigma;

    private ServiceOrderReasonModel motivoOs;

    @Schema(description =  "Service order description", example = "Any description")
    private String descricao;

    @Schema(description =  "Service order priority", example = "High")
    private String prioridadeOs;

    @Schema(description =  "Request client name", example = "John")
    private String solicitante;

    private ClientResumeModel cliente;

    private Dvr dvr;

    @Schema(description =  "Service open date", example = "2020-10-20 12:00:00")
    private OffsetDateTime dataAbertura;

    @Schema(description =  "Service order status", example = "true")
    private Boolean fechado;

}
