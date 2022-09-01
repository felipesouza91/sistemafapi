package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "DvrFilter")
public class DvrFilter {

	@Schema(description =  "Client id", example = "1")
	private Integer codCliente;

	@Schema(description =  "Client name", example = "Any Client name")
	private String nomeCliente;

	@Schema(description =  "DVR serial number", example = "XXXXXXXXX")
	private String numeroSerie;

	@Schema(description =  "Manufacturer name", example = "Tecvoz")
	private String fabricante;

	@Schema(description =  "DVR model", example = "Any DVR Model")
	private String modelo;

	
}
