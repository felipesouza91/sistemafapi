package com.sistemaf.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSimpleModel {
	
	private Long id;
	
	private Boolean ativo;
	
	private String nome;
		
	private String apelido;
	
	private Long idGrupoAcesso;
	
	private String nomeGrupoAcesso;

}
