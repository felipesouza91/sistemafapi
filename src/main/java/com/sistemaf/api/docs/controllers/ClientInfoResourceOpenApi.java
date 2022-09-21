package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.InfoInputModel;
import com.sistemaf.api.dto.model.ClientInfoModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.InformacaoFilter;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name= "Client Information")
@SecurityRequirement(name = "security_auth")
public interface ClientInfoResourceOpenApi {

    @Operation(summary = "Find Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
     Page<ClientInfoModel> listarInformacoes(
            @Parameter( description = "Client id", required = true, example = "1")
            @PathVariable Long clientId,
            @ParameterObject InformacaoFilter filter,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Find Clint Information by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description="Code invalid",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description="Client information not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
     ClientInfoModel listarInformacaoPorId(
            @Parameter( description = "Client id", required = true, example = "1")  @PathVariable Long clientId,
            @Parameter( description = "Client information id", required = true, example = "1") @PathVariable Long code);



    @Operation(summary = "Create Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description="Client information created"),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
     ClientInfoModel salvaInformacao(
            @Parameter(name = "body", required = true, description = "Client information") @Valid @RequestBody InfoInputModel info,
            @Parameter(required = true, description = "Client id", example = "1") @PathVariable Long clientId,
            HttpServletResponse response);

    @Operation(summary = "Update Clint Information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description="Client information updated"),
            @ApiResponse(responseCode = "404", description="Client not found",
                    content = @Content(	schema =  @Schema(implementation = Problem.class))),
    })
     ClientInfoModel atualizarInformacao(
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
    void removerInfo( @Parameter(required = true, description = "Client information id", example = "1") @PathVariable Long code);
}
