package com.sistemaf.domain.projection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Product Resume")
public class ResumoProduto {

	@Schema(description =  "Product code", example = "1")
	private Long id;

	@Schema(description =  "Product model", example = "MODEL Name")
	private String modelo;

	@Schema(description =  "Product value", example = "151,00")
	private Float valor;

	@Schema(description =  "Manufacturer code", example = "1")
	private Long idFabricante;

	@Schema(description =  "Manufacturer name", example = "Manufacturer name")
	private String fabricanteNome;
}
