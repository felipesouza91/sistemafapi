package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(name = "Stock Item Model Resume")
public class StockitemResumeDTO {

    @Schema(description =  "Stock Item Id", example = "1")
    private UUID id;
    @Schema(description =  "Serial ", example = "SD0000")
    private String serial;
    @Schema(description =  "Active status", example = "true")
    private Boolean active;
    @Schema(description =  "Product name", example = "Product name")
    private String productModel;
    @Schema(description =  "Manufacture name", example = "Manufacture name")
    private String manufactureName;


}
