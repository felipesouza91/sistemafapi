package com.sistemaf.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope=Cliente.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="codigo_service")
	private Integer codigoService;
	
	@Column(name="codigo_particao")
	private String codigoParticao;
	
	@Column(name="razao_social",nullable=false)
	private String razaoSocial;
	
	@Column(nullable=false)
	private String fantazia;
	
	@Size(min=8, max=12)
	private String telefone1;
	
	@Size(min=8, max=12)
	private String telefone2;
	
	@Column(nullable=false, unique=true)
	private String dominio;
	
	@Embedded
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name="codigo_grupo")
	private Grupo grupo;
	
	@NotNull
	private Boolean ativo;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade=CascadeType.DETACH)
	private List<ClienteInformacao> informacoes = new ArrayList<ClienteInformacao>();
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	private List<Particao> listParticoes = new ArrayList<Particao>();

}
