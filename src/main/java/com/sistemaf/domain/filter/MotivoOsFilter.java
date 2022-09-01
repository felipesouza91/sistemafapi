package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ServiceOrderReasonFilter")
public class MotivoOsFilter {

	@Schema(description =  "Reason description", example = "Some cause")
	private String descricao;
	
}
