package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.DvrFilter;
import com.sistemaf.domain.model.Dvr;
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

@Api(tags = "Dvr")
public interface DvrResourceOpenApi {

    @Operation(summary = "Find Dvr´s")
    Page<Dvr> listar(DvrFilter dvrFilter, Pageable pageable);

    @Operation(summary = "Find Dvr by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="DVR not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<Dvr> listar(@Parameter(description = "DVR code",example = "1") @PathVariable Long codigo);

    @Operation(summary = "Find Dvr by code")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description="DVR created")
    })
    ResponseEntity<Dvr> salvar(
            @Parameter( name = "body",description = "DVR information Fields",required = true ) @RequestBody @Valid Dvr dvr,
            HttpServletResponse response);

    @Operation(summary = "Update DVR by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="DVR updated"),
            @ApiResponse(responseCode = "404", description="DVR not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<Dvr> atualizar(
            @Parameter(description = "DVR code",example = "1") @PathVariable Long codigo,
            @Parameter( name = "body",description = "DVR information Fields to update",required = true ) @Valid @RequestBody Dvr dvr);

    @Operation(summary = "Delete DVR by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="DVR deleted success"),
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="DVR not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    void remover(@Parameter(description = "DVR code",example = "1", required = true) @PathVariable Long codigo);
}
