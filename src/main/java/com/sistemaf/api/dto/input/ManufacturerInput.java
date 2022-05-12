package com.sistemaf.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Manufacturer Input Model")
public class ManufacturerInput {

    @NotBlank
    @NotNull
    @Size(min=1,max=70)
    @ApiModelProperty(value = "Manufacturer name", example = "Name")
    private String descricao;

}
