package com.sistemaf.domain.model.definition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemaf.domain.model.Particao;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ConfigParticao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name="habilita_teste")
	private Boolean habilitaTeste; 
	
	@Column(name="tempo_teste") 
	private Long tempoTeste;
	
	@OneToOne
	@JoinColumn(name="codigo_particao", referencedColumnName="id")
	@JsonBackReference
	private Particao particao;

}
