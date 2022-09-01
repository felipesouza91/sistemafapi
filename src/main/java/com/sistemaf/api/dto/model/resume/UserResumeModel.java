package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "User Resume Model")
public class UserResumeModel {

    @Schema(description = "Codigo do usuario" , example = "1")
    private Long id;

    @Schema(description = "Nome do usuario" , example = "1")
    private String nome;
}
