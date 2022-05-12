package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.OrdemServicoFilter;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.projection.ResumOrdemServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

@Api(tags = "Service Order")
public interface ServiceOrderResourceOpenApi {

    @Operation(summary = "Find Service orders")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Show resume information",name = "resumo",paramType = "query",
            type = "string", required = false, example = "",defaultValue = "")
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
