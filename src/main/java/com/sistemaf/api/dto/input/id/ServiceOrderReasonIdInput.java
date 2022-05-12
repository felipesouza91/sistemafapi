package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Service order reason id input")
public class ServiceOrderReasonIdInput {

    @NotNull
    @ApiModelProperty(value = "Service order reason id", example = "1")
    private Long id;
}
