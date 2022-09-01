package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema( name ="Group Input")
public class GroupInput {

    @NotNull
    @Size(min=3 , max=70)
    @Schema(description = "Group name", example = "Any name")
    private String nome;
}
