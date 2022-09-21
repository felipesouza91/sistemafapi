package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ServiceOrderReasonInput;
import com.sistemaf.api.dto.model.ServiceOrderReasonModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.MotivoOsFilter;
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

@Tag(name = "Service Order Reason")
@SecurityRequirement(name = "security_auth")
public interface ServiceOrderReasonResourceOpenApi {

    @Operation(summary = "Find Service Order Reason")
    Page<ServiceOrderReasonModel> filtrar(@ParameterObject MotivoOsFilter filter,@ParameterObject Pageable pageable);

    @Operation(summary = "Find Service Order Reason by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema( implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Service order reason not found",
                    content = @Content(schema = @Schema( implementation = Problem.class)))
    })
    ResponseEntity<ServiceOrderReasonModel> listar(
            @Parameter(description = "Service order reason  code", example = "1",required = true) @PathVariable Long codigo);

    @Operation(summary = "Create a Service Order Reason")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Service order reason created success")
    })
    ResponseEntity<ServiceOrderReasonModel> salvar(
            @Parameter(name = "body", description = "Service order reason field", required = true)
            @RequestBody @Valid ServiceOrderReasonInput input,
            HttpServletResponse response);

    @Operation(summary = "Update Service Order Reason by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service order reason update success"),
            @ApiResponse(responseCode = "404", description = "Service order reason not found",
                    content = @Content(schema = @Schema( implementation = Problem.class)))
    })
    ResponseEntity<ServiceOrderReasonModel> atualizar(
            @Parameter(description = "Service order reason  code", example = "1",required = true) @PathVariable Long codigo,
            @Parameter(name = "body", description = "Service order reason update field", required = true)
            @Valid @RequestBody ServiceOrderReasonInput input);

    @Operation(summary = "Delete Service order reason by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Service order reason delete success"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema( implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Service order reason not found",
                    content = @Content(schema = @Schema( implementation = Problem.class)))
    })
    void remover( @Parameter(description = "Service order reason  code", example = "1",required = true)  @PathVariable Long codigo);
}
