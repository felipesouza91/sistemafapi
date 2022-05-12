package com.sistemaf.domain.filter;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ApiModel("ClosedOrderServiceFilter")
public class FechamentoOsFilter {

	@ApiModelProperty( value = "Closed order service code", example = "1")
	private String codigoOs;

	@ApiModelProperty( value = "Client code", example = "1")
	private String codigoCliente;

	@ApiModelProperty( value = "Technical name", example = "John")
	private String nomeTecnico;

	@ApiModelProperty( value = "Client name", example = "Client name")
	private String nomeCliente;

	@ApiModelProperty( value = "Closed cause description", example = "Video lost")
	private String motivoFechamento;

	@ApiModelProperty( value = "Initial Closed date", example = "2020-10-30")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFachamentoDe;

	@ApiModelProperty( value = "Final Closed date", example = "2020-10-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFechamentoAte;

	@ApiModelProperty( value = "Initial Visit date", example = "2020-01-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVisitaDe;

	@ApiModelProperty( value = "Final Visit date", example = "2020-03-31")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVisitaAte;

}
