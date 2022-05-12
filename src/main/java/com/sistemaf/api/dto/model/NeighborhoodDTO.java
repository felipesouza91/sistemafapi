package com.sistemaf.api.dto.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NeighborhoodDTO")
public class NeighborhoodDTO {

    @ApiModelProperty(value = "Neighborhood code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Neighborhood name", example = "Name")
    private String nome;

    @ApiModelProperty(value = "City information")
    private CityDTO cidade;
}
