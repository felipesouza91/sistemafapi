package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="bairro")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;

	@ManyToOne
	@JoinColumn(name="codigo_cidade")
	private Cidade cidade;

}
