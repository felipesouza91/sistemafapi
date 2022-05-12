package com.sistemaf.api.dto.model.resume;

import com.sistemaf.api.dto.model.ManufacturerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Product Resume Model")
public class ProductResumeModel {

    @ApiModelProperty(value = "Product code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Product model", example = "MODEL Name")
    private String modelo;

    @ApiModelProperty(value = "Description", example = "Any Description")
    private String descricao;

    @ApiModelProperty(value = "Product value", example = "151,00")
    private float valorUnitario;

    private ManufacturerDTO fabricante;

}
