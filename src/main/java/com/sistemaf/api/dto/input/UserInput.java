package com.sistemaf.api.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistemaf.api.dto.input.id.AccessGroupIdInput;
import com.sistemaf.domain.model.GrupoAcesso;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("User Input")
public class UserInput {

    @NotNull
    @ApiModelProperty(value = "User status", example = "true")
    private Boolean ativo;

    @NotBlank
    @NotNull
    @Size(max=50)
    @ApiModelProperty(value = "User name", example = "John")
    private String nome;

    @NotBlank
    @NotNull
    @Size(max=50)
    @ApiModelProperty(value = "User nick name", example = "jonh123")
    private String apelido;

    @NotBlank
    @NotNull
    @Size(max=150)
    @ApiModelProperty(value = "User password", example = "any_password")
    private String senha;

    @Valid
    @NotNull
    private AccessGroupIdInput grupoAcesso;
}
