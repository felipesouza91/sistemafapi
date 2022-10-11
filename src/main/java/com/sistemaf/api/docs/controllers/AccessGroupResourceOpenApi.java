package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.api.dto.model.AccessGroupDto;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Tag( name = "Access Group")
@SecurityRequirement(name = "security_auth")
public interface AccessGroupResourceOpenApi {

  @Operation(summary = "Find Access Group Resume")
  List<ResumoGrupoAcesso> listarResumo();

  @Operation(summary = "Find Access Group by id")
  @ApiResponses({
          @ApiResponse(responseCode = "400", description = "Code invalid",
                  content = @Content(schema = @Schema(implementation = Problem.class))),
          @ApiResponse(responseCode = "404", description = "Access Grupo not found",
                  content = @Content(schema = @Schema(implementation = Problem.class)))
  })
  ResponseEntity<AccessGroupDto> findByCode(
          @Parameter(description = "Access Group code", required = true, example = "1") @PathVariable Long codigo);

  @Operation(summary = "Create Access Group")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Access Group created")
  })
  ResponseEntity<AccessGroupDto> salvar(
          @Parameter(description = "Access Group fields", required = true, name = "body")
          @Valid
          @RequestBody AccessGroupInputData input,
          HttpServletResponse response);

  @Operation(summary = "Update an Access Group by code")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Update Access Group success"),
          @ApiResponse(responseCode = "404", description = "Access Group not found",
                  content = @Content(schema = @Schema(implementation = Problem.class)))
  })
  ResponseEntity<AccessGroupDto> atualizar(
          @Parameter(description = "Access Group code", required = true, example = "1") @PathVariable Long codigo,
          @Parameter(description = "Access Group updated fields", required = true, name = "body")
            @Valid @RequestBody AccessGroupInputData grupoAcesso);

  @Operation(summary = "Delete an Access Group by code")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Access Group Deletec success"),
          @ApiResponse(responseCode = "400", description = "Code invalid",
                  content = @Content(schema = @Schema(implementation = Problem.class))),
          @ApiResponse(responseCode = "404", description = "Access Grupo not found",
                  content = @Content(schema = @Schema(implementation = Problem.class)))
  })
  void remover(@Parameter(description = "Access Group code", required = true, example = "1") @PathVariable Long codigo);
}
