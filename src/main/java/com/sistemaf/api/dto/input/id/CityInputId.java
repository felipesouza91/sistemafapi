package com.sistemaf.api.dto.input.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("CityInputId")
public class CityInputId {
    @NotNull
    @ApiModelProperty(value = "City code", example = "1")
    private Long id;
}
