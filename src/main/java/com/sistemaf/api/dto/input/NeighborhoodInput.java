package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.CityInputId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema()
public class NeighborhoodInput {

    @NotNull
    @Size(min= 4, max = 70)
    @Schema(description  = "Neighborhood name", example = "Name")
    private String nome;

    @Valid
    @NotNull
    @Schema(description  = "City information")
    private CityInputId cidade;

}
