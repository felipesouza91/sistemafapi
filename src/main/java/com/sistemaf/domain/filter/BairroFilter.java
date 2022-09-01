package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "NeighborhoodFilter")
public class BairroFilter {

	@Schema(description =  "Neighborhood name", example = "Santo Cristo")
	private String nome;

	@Schema(description =  "City name", example = "Rio de janeiro")
	private String nomeCidade;

}
