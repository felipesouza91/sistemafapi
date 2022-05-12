package com.sistemaf.api.dto.input;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.api.dto.input.id.ManufacturerInputId;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.infrastructure.util.customserializer.FabricanteSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Product Input")
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
