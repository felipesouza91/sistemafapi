package com.sistemaf.domain.projection;

import java.time.OffsetDateTime;

public class ResumoVerificacaoGravacao {

	
	private Long id;
	
	private String status;
	
	private String hd;
	
	private int qtdGravacao;
	
	private OffsetDateTime dataTeste;
	
	private Long idDvr;
	
	private String fabricante;
	
	private String modeloDVr;
	
	private String nomeUsuario;
	
	private Long idCliente;
	
	private String nomeFantazia;

	public ResumoVerificacaoGravacao(Long id, String status, String hd, int qtdGravacao, OffsetDateTime dataTeste,
			Long idDvr, String fabricante ,String modeloDVr, String nomeUsuario, Long idCliente, String nomeFantazia) {
		super();
		this.id = id;
		this.status = status;
		this.hd = hd;
		this.qtdGravacao = qtdGravacao;
		this.dataTeste = dataTeste;
		this.idDvr = idDvr;
		this.fabricante = fabricante;
		this.modeloDVr = modeloDVr;
		this.nomeUsuario = nomeUsuario;
		this.idCliente = idCliente;
		this.nomeFantazia = nomeFantazia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public int getQtdGravacao() {
		return qtdGravacao;
	}

	public void setQtdGravacao(int qtdGravacao) {
		this.qtdGravacao = qtdGravacao;
	}

	public OffsetDateTime getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(OffsetDateTime dataTeste) {
		this.dataTeste = dataTeste;
	}

	public Long getIdDvr() {
		return idDvr;
	}

	public void setIdDvr(Long idDvr) {
		this.idDvr = idDvr;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModeloDVr() {
		return modeloDVr;
	}

	public void setModeloDVr(String modeloDVr) {
		this.modeloDVr = modeloDVr;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeFantazia() {
		return nomeFantazia;
	}

	public void setNomeFantazia(String nomeFantazia) {
		this.nomeFantazia = nomeFantazia;
	}
	
	
}
