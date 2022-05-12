package com.sistemaf.api.dto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Permission Model")
public class PermissionModel {

    @ApiModelProperty(value = "Permission code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Permission description", example = "Permision description")
    private String descricao;
}
