package com.sistemaf.api.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemaf.api.dto.model.resume.ServiceOrderResumeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Closed Order Model")
public class ClosedOrderDTO {

    @ApiModelProperty(value = "Closed Order Service code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Closed cause", example = "Cause")
    private String motivoFechamento;

    @ApiModelProperty(value = "Closed date", example = "2020-12-20 12:00:00")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFechamento;

    @ApiModelProperty(value = "Visit date", example = "2020-12-20 12:00:00")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataVisita;

    @ApiModelProperty(value = "Technician name", example = "John")
    private String tecnico;

    @ApiModelProperty(value = "Technician note", example = "any note")
    private String observcaoServico;

    private ServiceOrderResumeModel os;
}
