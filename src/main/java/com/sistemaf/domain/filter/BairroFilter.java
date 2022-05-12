package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NeighborhoodFilter")
public class BairroFilter {

	@ApiModelProperty(value = "Neighborhood name", example = "Santo Cristo")
	private String nome;

	@ApiModelProperty(value = "City name", example = "Rio de janeiro")
	private String nomeCidade;

}
