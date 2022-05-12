package com.sistemaf.domain.filter;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ApiModel("RecordingCheckFilter")
public class VerificarGravacaoFilter {

	@ApiModelProperty(value = "DVR code", example = "1")
	private Long codigoDvr;

	@ApiModelProperty(value = "Client code", example = "1")
	private Long codigoCliente;

	@ApiModelProperty(value = "Clinet name", example = "Any Client name")
	private String nomeCliente;

	@ApiModelProperty(value = "Recording Check date start", example = "2020-05-30")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVerificacaoDe;

	@ApiModelProperty(value = "Recording Check date end", example = "2020-05-35")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVerificacaoAte;

}
