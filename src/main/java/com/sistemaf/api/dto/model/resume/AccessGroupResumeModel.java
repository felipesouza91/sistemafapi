package com.sistemaf.api.dto.model.resume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Access Group Resume Model")
public class AccessGroupResumeModel {

    @ApiModelProperty(value = "Access Group code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Access Group description", example = "Any name")
    private String descricao;
}
