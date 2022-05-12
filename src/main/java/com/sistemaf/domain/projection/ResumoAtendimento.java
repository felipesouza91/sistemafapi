package com.sistemaf.domain.projection;

import java.time.LocalDateTime;

public class ResumoAtendimento {

	private Long id;
	
	private String descricao;
	
	private String solicitante;
	
	private Long idCliente;
	
	private String fantazia;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataTermino;
	
	private Long idUsuarioInicio;
	
	private String nomeUsuarioInicio;
	
	private Long idUsuarioTermino;
		
	public ResumoAtendimento(Long id, String descricao, Long idCliente, String fantazia, LocalDateTime dataInicio,
			LocalDateTime dataTermino, Long idUsuarioInicio, String nomeUsuarioInici, Long idUsuarioTermino, 
			String solicitante) {
		this.id = id;
		this.descricao = descricao;
		this.idCliente = idCliente;
		this.fantazia = fantazia;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.idUsuarioInicio = idUsuarioInicio;
		this.nomeUsuarioInicio = nomeUsuarioInici;
		this.idUsuarioTermino = idUsuarioTermino; 
		this.solicitante = solicitante;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolicitante() {
		return solicitante;
	}


	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}


	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getFantazia() {
		return fantazia;
	}

	public void setFantazia(String fantazia) {
		this.fantazia = fantazia;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Long getIdUsuarioInicio() {
		return idUsuarioInicio;
	}

	public void setIdUsuarioInicio(Long idUsuarioInicio) {
		this.idUsuarioInicio = idUsuarioInicio;
	}

	public String getNomeUsuarioInicio() {
		return nomeUsuarioInicio;
	}

	public void setNomeUsuarioInicio(String nomeUsuarioInicio) {
		this.nomeUsuarioInicio = nomeUsuarioInicio;
	}


	public Long getIdUsuarioTermino() {
		return idUsuarioTermino;
	}

	public void setIdUsuarioTermino(Long idUsuarioTermino) {
		this.idUsuarioTermino = idUsuarioTermino;
	}

}
