package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CityFilter")
public class CidadeFilter {

	@Schema(description =  "City name", example = "Rio de janeiro")
	private String nome;
	
}
