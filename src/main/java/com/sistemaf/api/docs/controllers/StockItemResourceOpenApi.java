package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.model.StockItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Stock Item")
@SecurityRequirement(name = "security_auth")
public interface StockItemResourceOpenApi {

    @Operation(summary = "Create a Stock Item")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stock Item    created success")
    })
    ResponseEntity<StockItemDTO> createNewStockItem(
            @Parameter(name = "body",description = "Stock Item Input") @Valid @RequestBody StockItemInput input,
            HttpServletResponse response);


}
