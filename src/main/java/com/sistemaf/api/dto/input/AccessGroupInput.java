package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.PermissionInputId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ApiModel("Access Group Input")
public class AccessGroupInput {

    @NotNull
    @ApiModelProperty(value = "Permission status", example = "true")
    private Boolean ativo;

    @NotNull
    @Size(max=50)
    @ApiModelProperty(value = "Permission description", example = "Any description")
    private String descricao;

    @Valid
    @NotNull
    @ApiModelProperty(value = "Permission list")
    private List<PermissionInputId> permissoes;
}
