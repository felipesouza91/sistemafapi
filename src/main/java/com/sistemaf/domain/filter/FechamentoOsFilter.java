package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Schema(name = "ClosedOrderServiceFilter")
public class FechamentoOsFilter {

	@Schema(description =  "Closed order service code", example = "1")
	private String codigoOs;

	@Schema(description =  "Client code", example = "1")
	private String codigoCliente;

	@Schema(description =  "Technical name", example = "John")
	private String nomeTecnico;

	@Schema(description =  "Client name", example = "Client name")
	private String nomeCliente;

	@Schema(description =  "Closed cause description", example = "Video lost")
	private String motivoFechamento;

	@Schema(description =  "Initial Closed date", example = "2020-10-30")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFachamentoDe;

	@Schema(description =  "Final Closed date", example = "2020-10-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFechamentoAte;

	@Schema(description =  "Initial Visit date", example = "2020-01-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVisitaDe;

	@Schema(description =  "Final Visit date", example = "2020-03-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVisitaAte;

}
