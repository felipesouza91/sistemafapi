package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.sistemaf.domain.model.custompk.ZonaPk;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="zona")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Zona {
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	private ZonaPk id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="cod_particao",insertable=false, updatable=false, referencedColumnName="id")
	@JsonIdentityReference(alwaysAsId=true)
	private Particao particao;
	
	@ManyToOne
	@JoinColumn(name="cod_produto")
	private Produto produto;

	@Column(name="id",insertable=false,updatable=false)
	private Long idZona;

	
}
