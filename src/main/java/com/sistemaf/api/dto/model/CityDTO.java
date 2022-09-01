package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CityDTO")
public class CityDTO {

	@Schema(description = "Codigo da cidade" , example = "1")
	private Long id;
	
	@Schema(description = "Nome da cidade" , example = "Rio de janeiro")
	private String nome;
	
}
