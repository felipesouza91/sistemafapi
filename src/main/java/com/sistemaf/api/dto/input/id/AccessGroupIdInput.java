package com.sistemaf.api.dto.input.id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Access Group Id Input")
public class AccessGroupIdInput {

    @Schema(description =  "Acess group id", example = "1")
    private  Long id;
}
