package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.CidadeInput;
import com.sistemaf.api.dto.model.CityDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.CidadeFilter;

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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "City")
public interface CityResourceOpenApi {

    @Operation(summary =  "Find Citys")
    Page<CityDTO> listar(CidadeFilter cidadeFilter, Pageable pageable);

    @Operation(summary =  "Find City by code")
    @ApiResponses({
        @ApiResponse(responseCode = "400", description="Code invalid",
            content = @Content(	schema =  @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description="City not found",
            content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<CityDTO> findByCode(
            @Parameter(description = "City code",example = "1", required = true) @PathVariable Long code);

    @Operation(summary =  "Create a city")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "201", description="City created"),
    })
    ResponseEntity<CityDTO> criar(
            @Parameter(description = "City fields", name = "body", required = true)
            @Valid @RequestBody CidadeInput input, HttpServletResponse response);

    @Operation(summary =  "Update a city by code")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "City update success"),
        @ApiResponse(responseCode = "404", description="City not found",
            content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<CityDTO> atualizar(
            @Parameter(description = "City code",example = "1", required = true) @PathVariable Long code,
            @Parameter(description = "City update fields", name = "body", required = true) @Valid @RequestBody CidadeInput input);

    @Operation(summary = "Delete City by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="City deleted success"),
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="City not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    void remover(@Parameter(description = "City code",example = "1", required = true)
                            @PathVariable Long code);
}
