package com.sistemaf.api.dto.input.id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Permission Input Id")
public class PermissionInputId {

    @Schema(description =  "Permission code", example = "1")
    private Long id;
}
