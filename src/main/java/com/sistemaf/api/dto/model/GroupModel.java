package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Group Model")
public class GroupModel {

    @Schema(description =  "Group code", example = "1")
    private Long id;

    @Schema(description =  "Group name", example = "Any group name")
    private String nome;
}
