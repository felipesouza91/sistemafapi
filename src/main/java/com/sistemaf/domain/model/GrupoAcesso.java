package com.sistemaf.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="grupo_acesso")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GrupoAcesso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private Boolean ativo;
	
	private String descricao;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinTable(name="grupo_permissao", joinColumns = @JoinColumn(name="codigo_grupo"),
			inverseJoinColumns = @JoinColumn(name="codigo_permissao"))
	private List<Permissao> permissoes;

}
