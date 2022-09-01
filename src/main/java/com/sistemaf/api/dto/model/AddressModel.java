package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Adress Model")
public class AddressModel {

    @Schema(description = "Street name", example = "19° Street")
    private String rua;

    @Schema(description = "Address Number", example = "123")
    private Long numero;

    @Schema(description = "Address complement", example = "house 3")
    private String complemento;

    @Schema(description = "Address reference", example = "house 3")
    private String referencia;

    private NeighborhoodDTO bairro;
}
