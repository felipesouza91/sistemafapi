package com.sistemaf.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("app-env")
public class SistemFApiProperty {
	
	private List<String> origemPermitida = new ArrayList<>();
	
	@NotBlank
	private String apiUrl;
	
	private String apiIntegrationUrl = null;
	
	private String pastaBackup = "backup";

	private int accessTokenValiditySeconds = 1800 ; // 30 minutos

	private int refreshTokenValiditySeconds = 3600*12; // 24 hours
	
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

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public int getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
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
