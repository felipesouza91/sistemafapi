package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name="Service Order Resume Model")
public class ServiceOrderResumeModel {

    @Schema(description = "Order service name", example = "John")
    private Long id;
}
