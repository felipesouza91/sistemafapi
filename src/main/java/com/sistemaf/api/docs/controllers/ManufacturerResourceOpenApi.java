package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ManufacturerInput;
import com.sistemaf.api.dto.model.ManufacturerDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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

@Api(tags = "Manufacturer")
public interface ManufacturerResourceOpenApi {

    @Operation(summary = "Find Manufacturers")
    ResponseEntity<Page<ManufacturerDTO>> getAll(
            @Parameter(description = "Manufactuer name", example = "Intelbras") @RequestParam(required = false) String nome,
            Pageable page);

    @Operation(summary = "Find Manufacturer by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Manufacture not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<ManufacturerDTO> getById(
            @Parameter(description = "Manufacturer code", example = "1", required = true) @PathVariable Long id);

    @Operation(summary = "Create a Manufacturer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Manufacturer created success")
    })
    ResponseEntity<ManufacturerDTO> save(
            @Parameter(name = "body",description = "Manufacturer fields") @Valid @RequestBody ManufacturerInput input,
            HttpServletResponse response);

    @Operation(summary = "Update Manufacturer by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Manufacturer updated",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Manufacture not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<ManufacturerDTO> update(
            @Parameter(description = "Manufacturer code", example = "1", required = true) @PathVariable Long id,
            @Parameter(name = "body",description = "Manufacturer updated fields")  @Valid @RequestBody ManufacturerInput input);

    @Operation(summary = "Delete Manufacturer by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Manufacturer deleted"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Manufacture not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void delete(@Parameter(description = "Manufacturer code", example = "1", required = true) @PathVariable Long id);
}
