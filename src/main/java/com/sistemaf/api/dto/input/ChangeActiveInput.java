package com.sistemaf.api.dto.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema()
public class ChangeActiveInput {

    @NotNull
    @Schema(description =  "Status item", example = "true")
    private Boolean active;
}
