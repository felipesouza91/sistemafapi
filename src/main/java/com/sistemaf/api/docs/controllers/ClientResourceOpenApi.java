package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ClientInput;
import com.sistemaf.api.dto.model.ClientModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.ClienteFilter;
import com.sistemaf.domain.model.Cliente;
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

@Api(tags = "Client")
public interface ClientResourceOpenApi {

    @Operation(summary =  "Find Clients" )
    public Page<ClientModel> filtrar(ClienteFilter clienteFilter, Pageable pageable);

    @Operation(summary =  "Find Client by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ResponseEntity<ClientModel> listar(
            @Parameter(required = true, description = "Client id", example = "1") @PathVariable Long codigo);

    @Operation(summary =  "Create a client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description="Client created"),
    })
    public ResponseEntity<ClientModel> salvar(
            @Parameter(required = true, description = "Client fields", name = "body")
            @Valid @RequestBody ClientInput cliente,
            HttpServletResponse response);

    @Operation(summary =  "Update Client by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="Client update success"),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ResponseEntity<ClientModel> atualizar(
            @Parameter(required = true, description = "Client id", example = "1")  @PathVariable Long codigo,
            @Parameter(required = true, description = "Client updated fields", name = "body") @Valid @RequestBody ClientInput cliente);

    @Operation(summary =  "Delete Client by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="Client delete success"),
            @ApiResponse(responseCode = "400", description="Code invalid ",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public void remover(@Parameter(required = true, description = "Client id", example = "1") @PathVariable Long codigo);
}
