package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ClientFilter")
public class ClienteFilter {

	@Schema(description =  "Service code", example = "12034")
	private Integer service;

	@Schema(description =  "Partition code", example = "1234")
	private String codigoParticao;

	@Schema(description =  "Client status", example = "true")
	private Boolean ativo;

	@Schema(description =  "Fantasy name", example = "Any Fantasy name")
	private String fantazia;

	@Schema(description =  "Company name", example = "Any Company name")
	private String razaoSocial;

	@Schema(description =  "Street address", example = "Rua Santo Cristo")
	private String endereco;

	@Schema(description =  "Neighborhood name", example = "Santo Cristo")
	private String bairro;

	@Schema(description =  "City name", example = "Rio de janeiro")
	private String cidade;

	@Schema(description =  "Domain name", example = "domain.site.com")
	private String dominio;

	@Schema(description =  "Company group name", example = "Company Group")
	private String nomeGrupo;

}
