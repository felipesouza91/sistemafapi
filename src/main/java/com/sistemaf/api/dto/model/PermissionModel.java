package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Permission Model")
public class PermissionModel {

    @Schema(description =  "Permission code", example = "1")
    private Long id;

    @Schema(description =  "Permission description", example = "Permision description")
    private String descricao;
}
