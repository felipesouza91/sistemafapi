package com.sistemaf.api.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InfoInputModel {

    @NotBlank
    @NotNull
    @ApiModelProperty(value = "Descrição da informação",example = "nova descricao", required = true)
    private String descricao;
}
