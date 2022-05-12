package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.model.NeighborhoodDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.BairroFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Neighborhood")
public interface NeighborhoodResourceOpenApi {

    @ApiOperation("Find neighborhood")
    public Page<NeighborhoodDTO>  pesquisar(BairroFilter bairroFilter, Pageable pageable);

    @ApiOperation("Find neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ResponseEntity<NeighborhoodDTO> findByCode(
            @Parameter(description = "Neighborhood code",example = "1", required = true) @PathVariable Long code);

    @ApiOperation("Create a neighborhood")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description="Neighborhood created"),
    })
    public ResponseEntity<NeighborhoodDTO> save(
            @Parameter(name = "body", description = "Neighborhood create fields", required = true)
            @Valid @RequestBody NeighborhoodInput inputBody,
            HttpServletResponse response);

    @ApiOperation("Update neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="Neighborhood update success"),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ResponseEntity<NeighborhoodDTO> atualizar(
            @Parameter(description = "Neighborhood code", example = "1", required = true) @PathVariable Long code,
            @Parameter(name = "body", description = "Neighborhood updated fields", required = true )
            @Valid @RequestBody NeighborhoodInput inputBody);

    @ApiOperation("Delete neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="Neighborhood delete success"),
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public void remover(@ApiParam(value = "Neighborhood code", example = "1") @PathVariable Long code);
}
