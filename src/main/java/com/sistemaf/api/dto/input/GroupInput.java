package com.sistemaf.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Group Input")
public class GroupInput {

    @NotNull
    @Size(min=3 , max=70)
    @ApiModelProperty(value = "Group name", example = "Any name")
    private String nome;
}
