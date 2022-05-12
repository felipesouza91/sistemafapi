package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.input.id.PermissionInputId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@ApiModel("Access Group Model")
public class AccessGroupModel {

    @ApiModelProperty(value = "Access group code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Permission status", example = "true")
    private Boolean ativo;

    @ApiModelProperty(value = "Permission description", example = "Any description")
    private String descricao;

    @ApiModelProperty(value = "Permission list")
    private List<PermissionModel> permissoes;
}
