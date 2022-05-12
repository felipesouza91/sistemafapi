package com.sistemaf.api.dto.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistemaf.api.dto.model.resume.AccessGroupResumeModel;
import com.sistemaf.domain.model.GrupoAcesso;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("User Model")
public class UserModel {

    @ApiModelProperty(value = "User code", example = "1")
    private Long id;

    @ApiModelProperty(value = "User status", example = "true")
    private Boolean ativo;

    @ApiModelProperty(value = "User name", example = "John")
    private String nome;

    @ApiModelProperty(value = "User nick name", example = "jonh123")
    private String apelido;

    private AccessGroupResumeModel grupoAcesso;
}
