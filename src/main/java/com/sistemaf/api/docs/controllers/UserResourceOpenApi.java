package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.UserInput;
import com.sistemaf.api.dto.model.UserModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.UsuarioFilter;
import com.sistemaf.domain.projection.UserSimpleModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "User")
@SecurityRequirement(name = "security_auth")
public interface UserResourceOpenApi {

    @Operation(summary = "Find system users informations")
    Page<UserModel> filtar(UsuarioFilter filter, Pageable page);

    @Operation(summary = "Find resume system users informations",
            parameters = @Parameter(name = "resume"))
    List<UserSimpleModel> resumo();

    @Operation(summary = "Find system user by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "System user not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<UserModel> buscarPorCodigo(
            @Parameter(description = "System user code", required = true, example = "1") @PathVariable Long codigo);

    @Operation(summary = "Create a system user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "System user created")
    })
    ResponseEntity<UserModel> salvar(
            @Parameter(name = "body", description = "System users field", required = true) @Valid @RequestBody UserInput usuario,
            HttpServletResponse response);

    @Operation(summary = "Update system user by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "System user update success",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "System user not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })

    ResponseEntity<UserModel> atualizar(
            @Parameter(description = "System user code", required = true, example = "1") @PathVariable Long codigo,
            @Parameter(name = "body", description = "System users updated field", required = true) @Valid @RequestBody UserInput usuario);
}
