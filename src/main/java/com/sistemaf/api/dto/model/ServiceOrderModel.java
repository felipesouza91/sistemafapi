package com.sistemaf.api.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.api.dto.model.resume.ClientResumeModel;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.infrastructure.util.customserializer.ClienteSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Service Order Model")
public class ServiceOrderModel {

    @ApiModelProperty(value = "Service order code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Service code (integration code Inside Sistemas)", example = "1555")
    private Integer codigoService;

    @ApiModelProperty(value = "Service code (integration code Sigma)", example = "1555")
    private Integer codigoSigma;

    private ServiceOrderReasonModel motivoOs;

    @ApiModelProperty(value = "Service order description", example = "Any description")
    private String descricao;

    @ApiModelProperty(value = "Service order priority", example = "High")
    private String prioridadeOs;

    @ApiModelProperty(value = "Request client name", example = "John")
    private String solicitante;

    private ClientResumeModel cliente;

    private Dvr dvr;

    @ApiModelProperty(value = "Service open date", example = "2020-10-20 12:00:00")
    private LocalDateTime dataAbertura;

    @ApiModelProperty(value = "Service order status", example = "true")
    private Boolean fechado;

}
