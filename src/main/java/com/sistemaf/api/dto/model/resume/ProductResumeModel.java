package com.sistemaf.api.dto.model.resume;

import com.sistemaf.api.dto.model.ManufacturerDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Product Resume Model")
public class ProductResumeModel {

    @Schema(description = "Product code", example = "1")
    private Long id;

    @Schema(description = "Product model", example = "MODEL Name")
    private String modelo;

    @Schema(description = "Description", example = "Any Description")
    private String descricao;

    @Schema(description = "Product value", example = "151,00")
    private float valorUnitario;

    private ManufacturerDTO fabricante;

}
