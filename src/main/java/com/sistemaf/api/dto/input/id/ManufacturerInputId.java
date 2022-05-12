package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Manufacturer Input Id")
public class ManufacturerInputId {

    @NotNull
    @ApiModelProperty(value = "Manufacturer code", example = "1")
    private Long id;
}
