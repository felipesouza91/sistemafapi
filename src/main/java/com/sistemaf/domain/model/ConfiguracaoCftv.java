package com.sistemaf.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sistemaf.domain.model.definition.ConfigParticao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="config_part_cftv")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConfiguracaoCftv extends ConfigParticao{
		
	@NotNull
	@Column(name="porta_http", nullable=false)
	private Integer portaHttp;
	
	@NotNull
	@Column(name="porta_servico", nullable=false)
	private Integer portaServico;
	
	@NotNull
	@Size(min=7, max=20)
	@Column(nullable=false)
	private String ip;
	
	@NotNull
	@Size(min=7, max=20)
	@Column(nullable=false)
	private String mascara;
	
	@NotNull
	@Size(min=7, max=20)
	@Column(nullable=false)
	private String gateway;
	
	@NotNull
	@Size(min=7, max=20)
	@Column(name="dns_principal",nullable=false)
	private String dnsPrincipal;
	
	@Column(name="dns_alternativo")	
	@Size(min=7, max=20)
	private String dnsAlternativo;
	
	@Size(min=7, max=50)
	@Column(name="numero_serie",unique=true)
	private String numeroSerie;
	
	@Column(name= "somente_cloud")
	private Boolean somenteCloud;

}
