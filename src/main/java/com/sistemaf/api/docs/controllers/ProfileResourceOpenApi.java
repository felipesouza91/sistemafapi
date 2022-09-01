package com.sistemaf.api.docs.controllers;

import com.sistemaf.api.dto.input.UpdatePasswordInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Profile")
public interface ProfileResourceOpenApi {

    @Operation(summary = "Change user password")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Password update success")
    })
    void updatePassword(
            @Parameter(name = "body", description = "Password update fields", required = true)
                @Valid @RequestBody UpdatePasswordInput input);
}
