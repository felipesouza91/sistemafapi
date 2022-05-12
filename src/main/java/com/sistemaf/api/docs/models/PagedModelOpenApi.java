package com.sistemaf.api.docs.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedModelOpenApi<T> {

    @ApiModelProperty(value = "Content elements")
    private List<T> content;

    @ApiModelProperty(value = "Size elements per page", example = "10")
    private Long size;

    @ApiModelProperty(value = "Total size elements", example = "30")
    private Long totalElements;

    @ApiModelProperty(value = "Total pages", example = "5")
    private Long totalPages;

    @ApiModelProperty(value = "Current page number, start with 0", example = "5")
    private Long number;
}
