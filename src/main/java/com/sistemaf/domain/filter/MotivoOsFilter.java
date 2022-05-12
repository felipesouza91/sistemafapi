package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "ServiceOrderReasonFilter")
public class MotivoOsFilter {

	@ApiModelProperty(value = "Reason description", example = "Some cause")
	private String descricao;
	
}
