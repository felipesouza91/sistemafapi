package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.InfoInputModel;
import com.sistemaf.api.dto.model.ClientInfoModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.InformacaoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Client Information")
public interface ClientInfoResourceOpenApi {

    @Operation(summary = "Find Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public Page<ClientInfoModel> listarInformacoes(
            @Parameter( description = "Client id", required = true, example = "1") @PathVariable Long clientId,
            InformacaoFilter filter, Pageable pageable);

    @Operation(summary = "Find Clint Information by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client information not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ClientInfoModel listarInformacaoPorId(
            @Parameter( description = "Client id", required = true, example = "1")  @PathVariable Long clientId,
            @Parameter( description = "Client information id", required = true, example = "1") @PathVariable Long code);



    @Operation(summary = "Create Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description="Client information created"),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ClientInfoModel salvaInformacao(
            @Parameter(name = "body", required = true, description = "Client information") @Valid @RequestBody InfoInputModel info,
            @Parameter(required = true, description = "Client id", example = "1") @PathVariable Long clientId,
            HttpServletResponse response);

    @Operation(summary = "Update Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="Client information updated"),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public ClientInfoModel atualizarInformacao(
            @Parameter(required = true, description = "Client information id", example = "1") @PathVariable Long code,
            @Parameter(required = true, description = "Client id", example = "1") @PathVariable Long clientId,
            @Parameter(name = "body", required = true, description = "Update Client information") @Valid @RequestBody InfoInputModel info);

    @Operation(summary = "Delete Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description="Client information deleted"),
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client information not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
    public  void removerInfo( @Parameter(required = true, description = "Client information id", example = "1") @PathVariable Long code);
}
