package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Schema(name = "RecordingCheckFilter")
public class VerificarGravacaoFilter {

	@Schema(description =  "DVR code", example = "1")
	private Long codigoDvr;

	@Schema(description =  "Client code", example = "1")
	private Long codigoCliente;

	@Schema(description =  "Clinet name", example = "Any Client name")
	private String nomeCliente;

	@Schema(description =  "Recording Check date start", example = "2020-05-30")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVerificacaoDe;

	@Schema(description =  "Recording Check date end", example = "2020-05-35")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVerificacaoAte;

}
