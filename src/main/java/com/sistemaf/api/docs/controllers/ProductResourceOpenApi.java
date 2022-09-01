package com.sistemaf.api.docs.controllers;


import com.sistemaf.api.dto.input.ProductInput;
import com.sistemaf.api.dto.model.ProductModel;
import com.sistemaf.api.dto.model.resume.ProductResumeModel;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.ProdutoFiltro;
import com.sistemaf.domain.projection.ResumoProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Product")
public interface ProductResourceOpenApi {

    @Operation(summary = "Find products")
    Page<ProductResumeModel> listar(ProdutoFiltro produtoFilter, Pageable pageable);

    @Operation(summary = "Find products resume")
    Page<ResumoProduto> listarResumo(ProdutoFiltro produtoFilter, Pageable pageable);

    @Operation(summary = "Find product by code")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "400", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<ProductModel> listar(
            @Parameter(description = "Product code", required = true, example = "1") @PathVariable Long codigo);

    @Operation(summary = "Create a product")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Code invalid")
    })
    ResponseEntity<ProductModel> criar(
            @Parameter(name = "body", description = "Product fields", required = true)
            @Valid @RequestBody ProductInput input,
            HttpServletResponse response);

    @Operation(summary = "Update product by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product update success"),
            @ApiResponse(responseCode = "400", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<ProductModel> atualizar(
            @Parameter(description = "Product code", required = true, example = "1")  @PathVariable Long codigo,
            @Parameter(name = "body", description = "Product update fields", required = true) @Valid @RequestBody ProductInput input);

    @Operation(summary = "Delete product by code")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted success"),
            @ApiResponse(responseCode = "400", description = "Code invalid",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "400", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void remover( @Parameter(description = "Product code", required = true, example = "1") @PathVariable Long codigo) ;
}
