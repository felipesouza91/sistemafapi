package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema()
public class ManufacturerInput {

    @NotBlank
    @NotNull
    @Size(min=1,max=70)
    @Schema(description  = "Manufacturer name", example = "Name")
    private String descricao;

}
