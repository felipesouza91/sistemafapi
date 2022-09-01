package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "Product Model")
public class ProductModel {

    @Schema(description =  "Product code", example = "1")
    private Long id;

    @Schema(description =  "Product model", example = "MODEL Name")
    private String modelo;

    @Schema(description =  "Description", example = "Any Description")
    private String descricao;

    @Schema(description =  "Product value", example = "151,00")
    private float valorUnitario;

    private ManufacturerDTO fabricante;

    private UserResumeModel createdBy;

    @Schema(description =  "Product created date", example = "2020-12-20 12:00:00")
    private LocalDateTime creationDate;

    private UserResumeModel lastModifiedBy;

    @Schema(description =  "Product updated date", example = "2020-12-20 12:00:00")
    private LocalDateTime lastModifiedDate;
}
