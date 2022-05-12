package com.sistemaf.domain.projection;

import java.time.LocalDateTime;

public class ResumOrdemServico {
	
	private Long id;
	
	private Integer codigoService;
	
	private String motivoOs;
	
	private String descricao;
	
	private String prioridadeOs;
	
	private String solicitante;
	
	private Long codigoCliente;
	
	private String nomeFantazia;
	
	private LocalDateTime dataAbertura;
	
	public ResumOrdemServico() {
		
	}
	
	public ResumOrdemServico(Long id, Integer codigoService, String motivoOs, String descricao, String prioridadeOs,
			String solicitante, Long codigoCliente, String nomeFantazia, LocalDateTime	 dataAbertura) {
		this.id = id;
		this.codigoService = codigoService;
		this.motivoOs = motivoOs;
		this.descricao = descricao;
		this.prioridadeOs = prioridadeOs;
		this.solicitante = solicitante;
		this.codigoCliente = codigoCliente;
		this.nomeFantazia = nomeFantazia;
		this.dataAbertura = dataAbertura;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoService() {
		return codigoService;
	}

	public void setCodigoService(Integer codigoService) {
		this.codigoService = codigoService;
	}

	public String getMotivoOs() {
		return motivoOs;
	}

	public void setMotivoOs(String motivoOs) {
		this.motivoOs = motivoOs;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrioridadeOs() {
		return prioridadeOs;
	}

	public void setPrioridadeOs(String prioridadeOs) {
		this.prioridadeOs = prioridadeOs;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeFantazia() {
		return nomeFantazia;
	}

	public void setNomeFantazia(String nomeFantazia) {
		this.nomeFantazia = nomeFantazia;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
}
