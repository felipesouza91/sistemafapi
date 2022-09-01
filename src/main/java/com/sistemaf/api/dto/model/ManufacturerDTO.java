package com.sistemaf.api.dto.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Manufacturer Model")
public class ManufacturerDTO {

    @Schema(description =  "Manufacturer code", example = "1")
    private Long id;

    @Schema(description =  "Manufacturer name", example = "Name")
    private String descricao;
}
