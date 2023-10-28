package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
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

	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private List<ClientFile> clientFiles = new ArrayList<>();
}
