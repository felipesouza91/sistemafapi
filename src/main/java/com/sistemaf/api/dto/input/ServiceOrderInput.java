package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.ClientIdInput;
import com.sistemaf.api.dto.input.id.DvrIdInput;
import com.sistemaf.api.dto.input.id.ServiceOrderReasonIdInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Service order input")
public class ServiceOrderInput {

    @ApiModelProperty(value = "Service code (integration code Inside Sistemas)", example = "1555")
    private Integer codigoService;

    @ApiModelProperty(value = "Service code (integration code Sigma)", example = "1555")
    private Integer codigoSigma;

    @Valid
    @NotNull
    private ServiceOrderReasonIdInput motivoOs;

    @NotBlank
    @NotNull
    @ApiModelProperty(value = "Service order description", example = "Any description")
    private String descricao;

    @NotBlank
    @NotNull
    @Size(min=4, max=11)
    @ApiModelProperty(value = "Service order priority", example = "High")
    private String prioridadeOs;

    @NotBlank
    @NotNull
    @Size(min=2, max=255)
    @ApiModelProperty(value = "Request client name", example = "John")
    private String solicitante;

    @Valid
    @NotNull
    private ClientIdInput cliente;

    @Valid
    private DvrIdInput dvr;
}
