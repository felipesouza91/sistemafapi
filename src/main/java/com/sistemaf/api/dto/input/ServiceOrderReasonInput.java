package com.sistemaf.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Service order Reason Input")
public class ServiceOrderReasonInput {

    @NotNull
    @NotBlank
    @Size(min=10, max=255)
    @ApiModelProperty(value = "Service order reason code", example = "1")
    private String descricao;
}
