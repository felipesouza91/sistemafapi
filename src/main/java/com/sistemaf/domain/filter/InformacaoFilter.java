package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ClientInformationFilter")
public class InformacaoFilter {

	@ApiModelProperty(value ="User name created client information", example = "admin")
	private String userCreate;

	@ApiModelProperty(value = "User id created client information", example = "1")
	private String idUserCreate;

}
