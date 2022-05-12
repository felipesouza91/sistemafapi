package com.sistemaf.domain.projection;

public class ResumoGrupoAcesso {
	
	private Long id;
	
	private Boolean ativo;
	
	private String descricao;
	
	public ResumoGrupoAcesso(Long id, Boolean ativo, String descricao) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
