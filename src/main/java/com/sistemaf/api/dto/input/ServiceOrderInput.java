package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.ClientIdInput;
import com.sistemaf.api.dto.input.id.DvrIdInput;
import com.sistemaf.api.dto.input.id.ServiceOrderReasonIdInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "Service order input")
public class ServiceOrderInput {

    @Schema(description = "Service code (integration code Inside Sistemas)", example = "1555")
    private Integer codigoService;

    @Schema(description = "Service code (integration code Sigma)", example = "1555")
    private Integer codigoSigma;

    @Valid
    @NotNull
    private ServiceOrderReasonIdInput motivoOs;

    @NotBlank
    @NotNull
    @Schema(description = "Service order description", example = "Any description")
    private String descricao;

    @NotBlank
    @NotNull
    @Size(min=4, max=11)
    @Schema(description = "Service order priority", example = "High")
    private String prioridadeOs;

    @NotBlank
    @NotNull
    @Size(min=2, max=255)
    @Schema(description = "Request client name", example = "John")
    private String solicitante;

    @Valid
    @NotNull
    private ClientIdInput cliente;

    @Valid
    private DvrIdInput dvr;
}
