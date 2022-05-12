package com.sistemaf.api.dto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Group Model")
public class GroupModel {

    @ApiModelProperty(value = "Group code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Group name", example = "Any group name")
    private String nome;
}
