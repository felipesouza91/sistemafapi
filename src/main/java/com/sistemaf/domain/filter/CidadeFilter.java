package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CityFilter")
public class CidadeFilter {

	@ApiModelProperty(value = "City name", example = "Rio de janeiro")
	private String nome;
	
}
