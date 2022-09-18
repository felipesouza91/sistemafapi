package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.model.NeighborhoodDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.BairroFilter;
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

@Tag(name = "Neighborhood")
@SecurityRequirement(name = "security_auth")
public interface NeighborhoodResourceOpenApi {

    @Operation(description =  "Find neighborhood")
    Page<NeighborhoodDTO>  pesquisar(@ParameterObject BairroFilter bairroFilter,@ParameterObject Pageable pageable);

    @Operation(description = "Find neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<NeighborhoodDTO> findByCode(
            @Parameter(description = "Neighborhood code", example = "1", required = true) @PathVariable Long code);

    @Operation(description = "Create a neighborhood")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description="Neighborhood created"),
    })
    ResponseEntity<NeighborhoodDTO> save(
            @Parameter(name = "body", description = "Neighborhood create fields", required = true)
            @Valid @RequestBody NeighborhoodInput inputBody,
            HttpServletResponse response);

    @Operation( description = "Update neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="Neighborhood update success"),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    ResponseEntity<NeighborhoodDTO> atualizar(
            @Parameter(description = "Neighborhood code", example = "1", required = true) @PathVariable Long code,
            @Parameter(name = "body", description = "Neighborhood updated fields", required = true)
            @Valid @RequestBody NeighborhoodInput inputBody);

    @Operation(description = "Delete neighborhood by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="Neighborhood delete success"),
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Neighborhood not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    void remover(@Parameter(description = "Neighborhood code", example = "1") @PathVariable Long code);
}
