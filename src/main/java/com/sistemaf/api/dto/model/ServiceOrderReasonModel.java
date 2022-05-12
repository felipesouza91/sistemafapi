package com.sistemaf.api.dto.model;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Service order reason Model")
public class ServiceOrderReasonModel {

    @ApiModelProperty(value = "Service order reason code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Service order reason description", example = "any description")
    private String descricao;

}
