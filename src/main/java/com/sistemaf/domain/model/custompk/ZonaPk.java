package com.sistemaf.domain.model.custompk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class ZonaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Size(max=3)
	@Column(name="num_zona")
	private String numeroZona;
	
	@Column(name="cod_particao")
	private Long codParticao;


}
