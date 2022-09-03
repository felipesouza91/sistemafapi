package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.PermissionInputId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Schema()
public class AccessGroupInput {

    @NotNull
    @Schema(description =  "Permission status", example = "true")
    private Boolean ativo;

    @NotNull
    @Size(max=50)
    @Schema(description =  "Permission description", example = "Any description")
    private String descricao;

    @Valid
    @NotNull
    @Schema(description =  "Permission list")
    private List<PermissionInputId> permissoes;
}
