package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "Access Group Model")
public class AccessGroupModel {

    @Schema(description = "Access group code", example = "1")
    private Long id;

    @Schema(description = "Permission status", example = "true")
    private Boolean ativo;

    @Schema(description = "Permission description", example = "Any description")
    private String descricao;

    @Schema(description = "Permission list")
    private List<PermissionModel> permissoes;
}
