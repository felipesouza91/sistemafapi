package com.sistemaf.api.docs.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Pageable")
public class PageableModelOpenApi {

    @ApiModelProperty(value = "Page number, start with 0",example = "0")
    private int page;
    @ApiModelProperty(value = "Elements quantity for page",example = "10")
    private int size;
    @ApiModelProperty(value = "Properties name for ordination", example = "nome,asc")
    private List<String> sort;
}
