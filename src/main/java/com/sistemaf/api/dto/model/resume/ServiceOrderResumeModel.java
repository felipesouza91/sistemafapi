package com.sistemaf.api.dto.model.resume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Service Order Resume Model")
public class ServiceOrderResumeModel {

    @ApiModelProperty(value = "Order service name", example = "John")
    private Long id;
}
