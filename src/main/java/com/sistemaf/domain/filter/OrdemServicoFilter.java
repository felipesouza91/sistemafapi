package com.sistemaf.domain.filter;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ApiModel("ServiceOrderFilter")
public class OrdemServicoFilter {

	@ApiModelProperty(value = "Inside order number", example = "1234")
	private Integer codigoService;

	@ApiModelProperty(value = "Client code", example = "1234")
	private Integer codigoCliente;

	@ApiModelProperty(value = "Service order reason", example = "some reason")
	private String motivoOs;

	@ApiModelProperty(value = "Service order priority", example = "High")
	private String prioridade;

	@ApiModelProperty(value = "Request name", example = "John")
	private String solicitante;

	@ApiModelProperty(value = "Client name", example = "Company 1")
	private String nomeCliente;

	@ApiModelProperty(value = "Service order open date start", example = "2020-12-20")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaDe;

	@ApiModelProperty(value = "Service order open date end", example = "2020-12-29")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaAte;
	

}
