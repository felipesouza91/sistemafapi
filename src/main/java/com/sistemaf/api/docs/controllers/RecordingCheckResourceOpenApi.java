package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.RecordingCheckInput;
import com.sistemaf.api.dto.model.RecordingCheckModel;
import com.sistemaf.api.dto.model.resume.RecordingCheckResumeModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.VerificarGravacaoFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Recording Check")
@SecurityRequirement(name = "security_auth")
public interface RecordingCheckResourceOpenApi {

    @Operation(summary = "Find Recording Check")
    Page<RecordingCheckModel> filtrar(@ParameterObject VerificarGravacaoFilter filter,@ParameterObject Pageable pageable);

    @Operation(summary = "Find Recording Check resume")
    Page<RecordingCheckResumeModel> resumo(@Parameter(required = false) VerificarGravacaoFilter filter, Pageable pageable);

    @Operation(summary = "Find Recording Check by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Recording Check not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<RecordingCheckModel> lista(
            @Parameter(description = "Recording Check code", example = "1", required = true) @PathVariable Long codigo);

    @Operation(summary = "Create a Recording Check")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Recording Check create succes")
    })
    ResponseEntity<RecordingCheckModel> salvar(
            @Parameter(name = "body",description = "Recording Check fields", required = true)
            @RequestBody @Valid RecordingCheckInput verificacaoGravacao,
            HttpServletResponse response);

    @Operation(summary = "Update Recording Check by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recording Check update succes"),
            @ApiResponse(responseCode = "404", description = "Recording Check not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<RecordingCheckModel> atualizar(
            @Parameter(description = "Recording Check code", example = "1", required = true) @PathVariable Long codigo,
            @Parameter(name = "body",description = "Recording Check update fields", required = true)
            @Valid @RequestBody RecordingCheckInput verificaroGravacao);

    @Operation(summary = "Delete Recording Check by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Recording Check delete succes"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Recording Check not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void remover(@Parameter(description = "Recording Check code", example = "1", required = true)  @PathVariable Long codigo);
}
