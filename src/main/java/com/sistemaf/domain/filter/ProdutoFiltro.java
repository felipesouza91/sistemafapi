package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductFilter")
public class ProdutoFiltro {

	@ApiModelProperty(value = "Model infor", example = "MHDX1104")
	private String modelo;

	@ApiModelProperty(value = "Manufacturer name", example = "SONY")
	private String nomeFabricante;

	@ApiModelProperty(value = "Manufacturer code", example = "1")
	private Long idFabricante;

}
