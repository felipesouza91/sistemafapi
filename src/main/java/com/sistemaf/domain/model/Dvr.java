package com.sistemaf.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="dvr")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dvr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Column(name= "habilita_verificao")
	private Boolean habilitaVerificao;
	
	@Column(name= "somente_cloud")
	private Boolean somenteCloud;
	
	@NotNull
	@Column(name="porta_http", nullable=false)
	private Integer portaHttp;
	
	@NotNull
	@Column(name="porta_servico", nullable=false)
	private Integer portaServico;
	
	@NotNull
	@Size(min=4 , max=20)
	@Column(nullable=false)
	private String fabricante;
	
	@NotNull
	@Size(min=4, max=50)
	@Column( name="modelo_dvr",nullable=false)
	private String modeloDvr;

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
	
	@Transient
	private boolean ultimoStatus;
	
	@NotNull
	@ManyToOne
	//TODO: Modificar @JsonSerialize(using=ClienteSerializer.class)
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;
	
}
