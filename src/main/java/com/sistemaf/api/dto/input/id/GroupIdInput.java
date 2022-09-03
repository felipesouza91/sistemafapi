package com.sistemaf.api.dto.input.id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema()
public class GroupIdInput {

    @NotNull
    @Schema(description =  "Group code", example = "1")
    private Long id;
}
