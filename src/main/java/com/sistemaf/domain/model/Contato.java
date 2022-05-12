package com.sistemaf.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="contato")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=100)
	@NotNull
	@Column(nullable=false, unique=true, length=100)
	private String nome;
	
	@Size(max=12)
	@Column(length=12)
	private String telefone;
	
	@Size(max=12)
	@Column(length=12)
	private String celular;

	@Size(max=255)
	private String senha;
	
	@Size(max=255)
	@Column(name="contra_senha")
	private String contraSenha;
	
	@Size(max=3)
	@Column(length=3)
	private String possicao;
	
	private Boolean usuario;
	
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	@JoinColumn(name="cod_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="cod_funcao")
	private Funcao funcao;

	
}
