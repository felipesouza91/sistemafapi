package com.sistemaf.api.dto.model.resume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("User Resume Model")
public class UserResumeModel {

    @ApiModelProperty(value = "Codigo do usuario" , example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do usuario" , example = "1")
    private String nome;
}
