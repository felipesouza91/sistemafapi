package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="cidade")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false, unique=true)
	private String nome;

}
