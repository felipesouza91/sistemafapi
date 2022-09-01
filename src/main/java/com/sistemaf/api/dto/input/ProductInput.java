package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.ManufacturerInputId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(name = "Product Input")
public class ProductInput {

    @NotBlank
    @NotNull
    private String modelo;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private float valorUnitario;

    @Valid
    @NotNull
    private ManufacturerInputId fabricante;

}
