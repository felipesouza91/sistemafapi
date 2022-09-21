package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="grupo_acesso")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
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
