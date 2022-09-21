package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="contato")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
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
