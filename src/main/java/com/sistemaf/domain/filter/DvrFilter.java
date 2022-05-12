package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DvrFilter")
public class DvrFilter {

	@ApiModelProperty(value = "Client id", example = "1")
	private Integer codCliente;

	@ApiModelProperty(value = "Client name", example = "Any Client name")
	private String nomeCliente;

	@ApiModelProperty(value = "DVR serial number", example = "XXXXXXXXX")
	private String numeroSerie;

	@ApiModelProperty(value = "Manufacturer name", example = "Tecvoz")
	private String fabricante;

	@ApiModelProperty(value = "DVR model", example = "Any DVR Model")
	private String modelo;

	
}
