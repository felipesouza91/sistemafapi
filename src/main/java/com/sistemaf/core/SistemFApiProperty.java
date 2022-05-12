package com.sistemaf.core;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app-env")
public class SistemFApiProperty {
	
	private List<String> origemPermitida = new ArrayList<>();
	
	@NotBlank
	private String apiUrl;
	
	private String apiIntegrationUrl = null;
	
	private String pastaBackup = "backup";
	
	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}

	public List<String> getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(List<String> origemPermitida) {
		this.origemPermitida = origemPermitida;
	}

	public String getApiIntegrationUrl() {
		return apiIntegrationUrl;
	}

	public void setApiIntegrationUrl(String apiIntegrationUrl) {
		this.apiIntegrationUrl = apiIntegrationUrl;
	}
	
	public String getPastaBackup() {
		return pastaBackup;
	}

	public void setPastaBackup(String pastaBackup) {
		this.pastaBackup = pastaBackup;
	}
	
	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}



	public static class Seguranca {
		private boolean enableHttps;

		private String jwtSecretKey;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

		public String getJwtSecretKey() {
			return jwtSecretKey;
		}

		public void setJwtSecretKey(String jwtSecretKey) {
			this.jwtSecretKey = jwtSecretKey;
		}
	}

	
}
