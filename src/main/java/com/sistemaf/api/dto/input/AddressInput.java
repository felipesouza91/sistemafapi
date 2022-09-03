package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.NeighborhoodIdInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema()
public class AddressInput {

    @NotNull
    @NotBlank
    @Schema(description =  "Street name", example = "19° Street")
    private String rua;

    @NotNull
    @Schema(description =  "Address Number", example = "123")
    private Long numero;

    @Schema(description =  "Address complement", example = "house 3")
    private String complemento;

    @Schema(description =  "Address reference", example = "house 3")
    private String referencia;

    @Valid
    @NotNull
    private NeighborhoodIdInput bairro;

}
