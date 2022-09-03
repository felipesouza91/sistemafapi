package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.OrdemServicoFilter;

import com.sistemaf.domain.projection.ResumOrdemServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Service Order")
@SecurityRequirement(name = "security_auth")
public interface ServiceOrderResourceOpenApi {

    @Operation(summary = "Find Service orders")
    @Parameters({
            @Parameter(description = "Show resume information", name = "resumo",in = ParameterIn.QUERY
              ,example = "resume")
    })
    Page<ServiceOrderModel> filtrar(OrdemServicoFilter filter, Pageable pageable);

    @Operation(summary = "Find Service orders resume",hidden = true)
    Page<ResumOrdemServico> resumir(OrdemServicoFilter filter, Pageable pageable);

    @Operation(summary = "Find Service order by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Service order not found",
            content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<ServiceOrderModel> lista(
            @Parameter(description = "Service order code", required = true, example = "1") @PathVariable Long codigo);

    @Operation(summary = "Save a Service order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create a service order")
    })
    ResponseEntity<ServiceOrderModel> salvar(
            @Parameter(name = "body", description = "Service order fields", required = true)
            @RequestBody @Valid ServiceOrderInput input,
            HttpServletResponse response);

    @Operation(summary = "Update service order by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update service order success"),
            @ApiResponse(responseCode = "404", description = "Service order not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<ServiceOrderModel> atualizar(
            @Parameter(description = "Service order code", required = true, example = "1") @PathVariable Long codigo,
            @Parameter(name = "body", description = "Service order fields", required = true)
            @Valid @RequestBody ServiceOrderInput input);

    @Operation(summary = "Delete service order by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delete service order success"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Service order not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void remover( @Parameter(description = "Service order code", required = true, example = "1") @PathVariable Long codigo);
}
