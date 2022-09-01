package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Service order reason Model")
public class ServiceOrderReasonModel {

    @Schema(description =  "Service order reason code", example = "1")
    private Long id;

    @Schema(description =  "Service order reason description", example = "any description")
    private String descricao;

}
