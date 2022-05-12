package com.sistemaf.api.dto.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Manufacturer Model")
public class ManufacturerDTO {

    @ApiModelProperty(value = "Manufacturer code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Manufacturer name", example = "Name")
    private String descricao;
}
