package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(name="Infor Input Model")
public class InfoInputModel {

    @NotBlank
    @NotNull
    @Schema(description  = "Descrição da informação",example = "nova descricao", required = true)
    private String descricao;
}
