package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Group Id Input")
public class GroupIdInput {

    @NotNull
    @ApiModelProperty(value = "Group code", example = "1")
    private Long id;
}
