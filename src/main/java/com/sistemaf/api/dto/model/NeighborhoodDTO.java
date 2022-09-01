package com.sistemaf.api.dto.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "NeighborhoodDTO")
public class NeighborhoodDTO {

    @Schema(description =  "Neighborhood code", example = "1")
    private Long id;

    @Schema(description =  "Neighborhood name", example = "Name")
    private String nome;

    @Schema(description =  "City information")
    private CityDTO cidade;
}
