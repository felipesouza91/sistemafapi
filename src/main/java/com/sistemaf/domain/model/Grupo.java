package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="grupo")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String nome;

}
