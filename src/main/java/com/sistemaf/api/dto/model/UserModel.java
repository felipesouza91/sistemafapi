package com.sistemaf.api.dto.model;


import com.sistemaf.api.dto.model.resume.AccessGroupResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(name = "User Model")
public class UserModel {

    @Schema(description =  "User code", example = "1")
    private Long id;

    @Schema(description =  "User status", example = "true")
    private Boolean ativo;

    @Schema(description =  "User name", example = "John")
    private String nome;

    @Schema(description =  "User nick name", example = "jonh123")
    private String apelido;

    private AccessGroupResumeModel grupoAcesso;
}
