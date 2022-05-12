package com.sistemaf.api.docs.controllers;

import com.sistemaf.domain.model.Particao;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Partition")
public interface PartitionResourceOpenApi {

    @Operation(summary = "Find Partition")
    List<Particao> findAll();

    @Operation(summary = "Create a Partition")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Partition created")
    })
    Particao save(@Parameter(name = "body", description = "Partition fields", required = true) @RequestBody @Valid Particao particao);
}
