package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFilter")
public class UsuarioFilter {

	@ApiModelProperty(value = "Employee name", example = "John")
	private String funcionario;

	@ApiModelProperty(value = "Employee username", example = "john81")
	private String apelido;

	@ApiModelProperty(value = "Employee Access grupo", example = "Administrator")
	private String grupoAcesso;
	
}
