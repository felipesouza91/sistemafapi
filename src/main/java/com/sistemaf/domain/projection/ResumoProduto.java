package com.sistemaf.domain.projection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Product Resume")
public class ResumoProduto {

	@ApiModelProperty(value = "Product code", example = "1")
	private Long id;

	@ApiModelProperty(value = "Product model", example = "MODEL Name")
	private String modelo;

	@ApiModelProperty(value = "Product value", example = "151,00")
	private Float valor;

	@ApiModelProperty(value = "Manufacturer code", example = "1")
	private Long idFabricante;

	@ApiModelProperty(value = "Manufacturer name", example = "Manufacturer name")
	private String fabricanteNome;
}
