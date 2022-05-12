package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Access Group Id Input")
public class AccessGroupIdInput {

    @ApiModelProperty(value ="Acess group id", example = "1")
    private  Long id;
}
