package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="usuario")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private Boolean ativo;
	
	private String nome;

	@Column(unique=true)
	private String apelido;
	
	private String senha;
	
	@OneToOne
	@JoinColumn(name="codigo_grupo", nullable=false)
	private GrupoAcesso grupoAcesso;

}
