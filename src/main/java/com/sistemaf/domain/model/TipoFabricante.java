package com.sistemaf.domain.model;



public enum TipoFabricante {
	
	TECVOZ("TECVOZ"),
	INTELBRAS("INTELBRAS"),
	VID8("VID8");

	private String descricao;
	
	TipoFabricante(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
