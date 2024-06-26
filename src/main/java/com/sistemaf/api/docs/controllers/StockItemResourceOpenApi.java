package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.ChangeActiveInput;
import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.model.StockItemDTO;
import com.sistemaf.api.dto.model.StockitemResumeDTO;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.domain.filter.StockItemFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "Stock Item")
@SecurityRequirement(name = "security_auth")
public interface StockItemResourceOpenApi {

    @Operation(summary = "Create a Stock Item" )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stock Item created success"),
            @ApiResponse(responseCode = "400", description = "Request body validation fails",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<StockItemDTO> createNewStockItem(
            @Parameter(name = "body",description = "Stock Item Input") @Valid @RequestBody StockItemInput input,
            HttpServletResponse response);


    @Operation(summary = "Find Stock items",
            parameters = @Parameter(name = "resume", allowEmptyValue = true,
                    required = true, explode = Explode.FALSE, in = ParameterIn.QUERY, example = "resume"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "A list with stock items data"),
    })
    ResponseEntity<Page<StockitemResumeDTO>> findStockItemsResume(@ParameterObject() StockItemFilter stockItemFilter, @ParameterObject() Pageable pageable);

    @Operation(summary = "Find Stock By Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock Item details"),
            @ApiResponse(responseCode = "404", description = "Stock Item not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<StockItemDTO> findStockItemById(@Parameter(required = true) UUID id);

    @Operation(summary = "Update a stock item")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Stock Item status changed"),
            @ApiResponse(responseCode = "400", description = "Request body validation fails",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Stock Item not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<StockItemDTO> updateStockItem(UUID id, StockItemInput stockItemInput);

    @Operation(summary = "Change stock item status")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Stock Item status changed"),
            @ApiResponse(responseCode = "400", description = "Request body validation fails",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Stock Item not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void changeActiveStockItem(UUID id, ChangeActiveInput active);
}

