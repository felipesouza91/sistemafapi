package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.CityInputId;
import com.sistemaf.domain.model.Cidade;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("NeighborhoodInput")
public class NeighborhoodInput {

    @NotNull
    @Size(min= 4, max = 70)
    @ApiModelProperty(value = "Neighborhood name", example = "Name")
    private String nome;

    @Valid
    @NotNull
    @ApiModelProperty(value = "City information")
    private CityInputId cidade;

}
