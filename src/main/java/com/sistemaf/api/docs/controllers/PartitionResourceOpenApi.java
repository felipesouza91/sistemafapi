package com.sistemaf.api.docs.controllers;

import com.sistemaf.domain.model.Particao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Partition")
@SecurityRequirement(name = "security_auth")
public interface PartitionResourceOpenApi {

    @Operation(summary = "Find Partition",hidden = true)
    List<Particao> findAll();

    @Operation(summary = "Create a Partition", hidden = true)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Partition created")
    })
    Particao save(@Parameter(name = "body", description = "Partition fields", required = true) @RequestBody @Valid Particao particao);
}
