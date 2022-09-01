package com.sistemaf.api.docs.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "Pageable")
public class PageableModelOpenApi {

    @Schema(description = "Page number, start with 0",example = "0")
    private int page;
    @Schema(description = "Elements quantity for page",example = "10")
    private int size;
    @Schema(description = "Properties name for ordination", example = "nome,asc")
    private List<String> sort;
}
