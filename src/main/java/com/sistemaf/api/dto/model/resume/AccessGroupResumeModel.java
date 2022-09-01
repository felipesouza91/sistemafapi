package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Access Group Resume Model")
public class AccessGroupResumeModel {

    @Schema(description = "Access Group code", example = "1")
    private Long id;

    @Schema(description = "Access Group description", example = "Any name")
    private String descricao;
}
