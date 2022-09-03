package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ClosedOrderInput;
import com.sistemaf.api.dto.model.ClosedOrderDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.FechamentoOsFilter;
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

@Tag(name = "Close Order of Service")
@SecurityRequirement(name = "security_auth")
public interface ClosedOrderServiceResourceOpenApi {

    @Operation(summary = "Find Close Order Services")
    Page<ClosedOrderDTO> list(FechamentoOsFilter filter, Pageable pageable);

    @Operation(summary = "Find Close Order Services by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Close Order Services not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<ClosedOrderDTO> list(
            @Parameter(description = "Cloded Order service code", example = "1", required = true) @PathVariable Long codigo);

    @Operation(summary = "Create a closed order service")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Closed order created"),
    })
    ResponseEntity<ClosedOrderDTO> salvar(
            @Parameter(description = "Closed Order Service fields", name = "body",required = true)
            @RequestBody @Valid ClosedOrderInput input,
            HttpServletResponse response);

    @Operation(summary = "Update Close Order Services by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Close Order Services Updated",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Close Order Services not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<ClosedOrderDTO> atualizar(
            @Parameter(description = "Cloded Order service code", example = "1", required = true)  @PathVariable Long codigo,
            @Parameter(description = "Closed Order Service updated fields", name = "body",required = true)
            @Valid @RequestBody ClosedOrderInput input);

    @Operation(summary = "Delete Close Order Services by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Close Order Services deleted"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Close Order Services not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void remover(
            @Parameter(description = "Cloded Order service code", example = "1", required = true)   @PathVariable Long codigo);
}
