package com.sistemaf.api.docs.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "PageModel")
public class PagedModelOpenApi<T> {

    @Schema(description = "Content elements")
    private List<T> content;

    @Schema(description = "Size elements per page", example = "10")
    private Long size;

    @Schema(description = "Total size elements", example = "30")
    private Long totalElements;

    @Schema(description = "Total pages", example = "5")
    private Long totalPages;

    @Schema(description = "Current page number, start with 0", example = "5")
    private Long number;
}
