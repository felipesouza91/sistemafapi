package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Service Order Input Id")
public class ServiceOrderInputId {

    @NotNull
    @ApiModelProperty(value = "Service order id",example = "1")
    private Long id;
}
