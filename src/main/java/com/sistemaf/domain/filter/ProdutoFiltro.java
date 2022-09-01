package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ProductFilter")
public class ProdutoFiltro {

	@Schema(description = "Model infor", example = "MHDX1104")
	private String modelo;

	@Schema(description = "Manufacturer name", example = "SONY")
	private String nomeFabricante;

	@Schema(description = "Manufacturer code", example = "1")
	private Long idFabricante;

}
