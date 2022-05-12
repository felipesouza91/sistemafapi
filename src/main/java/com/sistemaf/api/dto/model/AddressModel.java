package com.sistemaf.api.dto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Adress Model")
public class AddressModel {

    @ApiModelProperty(value = "Street name", example = "19° Street")
    private String rua;

    @ApiModelProperty(value = "Address Number", example = "123")
    private Long numero;

    @ApiModelProperty(value = "Address complement", example = "house 3")
    private String complemento;

    @ApiModelProperty(value = "Address reference", example = "house 3")
    private String referencia;

    private NeighborhoodDTO bairro;
}
