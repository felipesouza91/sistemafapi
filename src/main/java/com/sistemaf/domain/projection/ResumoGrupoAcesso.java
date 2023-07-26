package com.sistemaf.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumoGrupoAcesso {
	
	private Long id;
	
	private Boolean ativo;
	
	private String descricao;


}
