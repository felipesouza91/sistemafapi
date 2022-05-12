package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.NeighborhoodIdInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Address Input")
public class AddressInput {

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Street name", example = "19° Street")
    private String rua;

    @NotNull
    @ApiModelProperty(value = "Address Number", example = "123")
    private Long numero;

    @ApiModelProperty(value = "Address complement", example = "house 3")
    private String complemento;

    @ApiModelProperty(value = "Address reference", example = "house 3")
    private String referencia;

    @Valid
    @NotNull
    private NeighborhoodIdInput bairro;

}
