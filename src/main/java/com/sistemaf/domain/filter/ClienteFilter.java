package com.sistemaf.domain.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ClientFilter")
public class ClienteFilter {

	@ApiModelProperty(value = "Service code", example = "12034")
	private Integer service;

	@ApiModelProperty(value = "Partition code", example = "1234")
	private String codigoParticao;

	@ApiModelProperty(value = "Client status", example = "true")
	private Boolean ativo;

	@ApiModelProperty(value = "Fantasy name", example = "Any Fantasy name")
	private String fantazia;

	@ApiModelProperty(value = "Company name", example = "Any Company name")
	private String razaoSocial;

	@ApiModelProperty(value = "Street address", example = "Rua Santo Cristo")
	private String endereco;

	@ApiModelProperty(value = "Neighborhood name", example = "Santo Cristo")
	private String bairro;

	@ApiModelProperty(value = "City name", example = "Rio de janeiro")
	private String cidade;

	@ApiModelProperty(value = "Domain name", example = "domain.site.com")
	private String dominio;

	@ApiModelProperty(value = "Company group name", example = "Company Group")
	private String nomeGrupo;

}
