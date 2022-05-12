package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.GroupInput;
import com.sistemaf.api.dto.model.GroupModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.GrupoFilter;
import com.sistemaf.domain.model.Grupo;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Group")
public interface GroupResourceOpenApi {

    @Operation(summary = "Find Groups")
    Page<GroupModel> listar(GrupoFilter grupoFilter, Pageable pageable);

    @Operation(summary = "Find Group by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<GroupModel> lista(@Parameter(description = "Group code", required = true, example = "1") @PathVariable Long codigo);

    @Operation(summary = "Create a Group")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Group created success")
    })
    ResponseEntity<GroupModel> salvar(
            @Parameter(name = "body",description = "Group field", required = true) @RequestBody @Valid GroupInput input,
            HttpServletResponse response);

    @Operation(summary = "Update Group by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Group success"),
            @ApiResponse(responseCode = "404", description = "Group not found",
            content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<GroupModel> atualizar(
            @Parameter(description = "Group code", required = true, example = "1") @PathVariable Long codigo,
            @Parameter(name = "body",description = "Group update field", required = true) @Valid @RequestBody GroupInput input);

    @Operation(summary = "Delete Group By code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delete Group success"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void remover( @Parameter(description = "Group code", required = true, example = "1")  @PathVariable Long codigo);
}
