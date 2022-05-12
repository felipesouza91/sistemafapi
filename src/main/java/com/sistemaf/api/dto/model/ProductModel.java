package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Product Model")
public class ProductModel {

    @ApiModelProperty(value = "Product code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Product model", example = "MODEL Name")
    private String modelo;

    @ApiModelProperty(value = "Description", example = "Any Description")
    private String descricao;

    @ApiModelProperty(value = "Product value", example = "151,00")
    private float valorUnitario;

    private ManufacturerDTO fabricante;

    private UserResumeModel createdBy;

    @ApiModelProperty(value = "Product created date", example = "2020-12-20 12:00:00")
    private LocalDateTime creationDate;

    private UserResumeModel lastModifiedBy;

    @ApiModelProperty(value = "Product updated date", example = "2020-12-20 12:00:00")
    private LocalDateTime lastModifiedDate;
}
