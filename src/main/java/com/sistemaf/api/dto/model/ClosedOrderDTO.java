package com.sistemaf.api.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemaf.api.dto.model.resume.ServiceOrderResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "Closed Order Model")
public class ClosedOrderDTO {

    @Schema(description =  "Closed Order Service code", example = "1")
    private Long id;

    @Schema(description =  "Closed cause", example = "Cause")
    private String motivoFechamento;

    @Schema(description =  "Closed date", example = "2020-12-20 12:00:00")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFechamento;

    @Schema(description =  "Visit date", example = "2020-12-20 12:00:00")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataVisita;

    @Schema(description =  "Technician name", example = "John")
    private String tecnico;

    @Schema(description =  "Technician note", example = "any note")
    private String observcaoServico;

    private ServiceOrderResumeModel os;
}
