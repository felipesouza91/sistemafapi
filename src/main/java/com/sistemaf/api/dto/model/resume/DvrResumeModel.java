package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Dvr Resume Model")
public class DvrResumeModel {

    @Schema(description ="Dvr code", example = "1")
    private Long id;

    @Schema(description ="Manufacturer name", example = "Any manufacturer")
    private String fabricante;

    @Schema(description ="Model name", example = "Any model name")
    private String modeloDvr;

}
