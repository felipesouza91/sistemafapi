package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.AccessGroupIdInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "User Input")
public class UserInput {

    @NotNull
    @Schema(description = "User status", example = "true")
    private Boolean ativo;

    @NotBlank
    @NotNull
    @Size(max=50)
    @Schema(description = "User name", example = "John")
    private String nome;

    @NotBlank
    @NotNull
    @Size(max=50)
    @Schema(description = "User nick name", example = "jonh123")
    private String apelido;

    @NotBlank
    @NotNull
    @Size(max=150)
    @Schema(description = "User password", example = "any_password")
    private String senha;

    @Valid
    @NotNull
    private AccessGroupIdInput grupoAcesso;
}
