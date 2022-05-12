package com.sistemaf.api.dto.model.resume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Dvr Resume Model")
public class DvrResumeModel {

    @ApiModelProperty(value = "Dvr code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Manufacturer name", example = "Any manufacturer")
    private String fabricante;

    @ApiModelProperty(value = "Model name", example = "Any model name")
    private String modeloDvr;

}
