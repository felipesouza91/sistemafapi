package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ClientInformationFilter")
public class InformacaoFilter {

	@Schema(description = "User name created client information", example = "admin")
	private String userCreate;

	@Schema(description = "User id created client information", example = "1")
	private String idUserCreate;

}
