package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Schema(name = "ServiceOrderFilter")
public class OrdemServicoFilter {

	@Schema(description =  "Inside order number", example = "1234")
	private Integer codigoService;

	@Schema(description =  "Client code", example = "1234")
	private Integer codigoCliente;

	@Schema(description =  "Service order reason", example = "some reason")
	private String motivoOs;

	@Schema(description =  "Service order priority", example = "High")
	private String prioridade;

	@Schema(description =  "Request name", example = "John")
	private String solicitante;

	@Schema(description =  "Client name", example = "Company 1")
	private String nomeCliente;

	@Schema(description =  "Service order open date start", example = "2020-12-20")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaDe;

	@Schema(description =  "Service order open date end", example = "2020-12-29")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaAte;
	

}
