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
public class ServiceOrderReasonInput {

    @NotNull
    @NotBlank
    @Size(min=10, max=255)
    @Schema(description = "Service order reason code", example = "1")
    private String descricao;
}
