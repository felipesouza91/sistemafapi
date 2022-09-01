package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "UserFilter")
public class UsuarioFilter {

	@Schema(description =  "Employee name", example = "John")
	private String funcionario;

	@Schema(description =  "Employee username", example = "john81")
	private String apelido;

	@Schema(description =  "Employee Access grupo", example = "Administrator")
	private String grupoAcesso;
	
}
