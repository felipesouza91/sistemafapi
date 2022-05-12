package com.sistemaf.api.dto.model.resume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Client Resume Model")
public class ClientResumeModel {

    @ApiModelProperty(value = "Client code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Client Service code", example = "1123")
    private Integer codigoService;

    @ApiModelProperty(value = "Client partition code", example = "001")
    private String codigoParticao;

    @ApiModelProperty(value = "Social Name", example = "Any social name")
    private String razaoSocial;

    @ApiModelProperty(value = "Company Name", example = "Any company name")
    private String fantazia;

}
