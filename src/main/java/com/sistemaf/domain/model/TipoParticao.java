package com.sistemaf.domain.model;

public enum TipoParticao {
	
	
	CFTV("CFTV"),
	ALARME("ALARME"),
	CONTROLE_ACESSO("CONTROLE DE ACESSO");

	private String descricao;
	
	TipoParticao(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
}
