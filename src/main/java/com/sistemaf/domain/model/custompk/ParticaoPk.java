package com.sistemaf.domain.model.custompk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ParticaoPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "num_particao")
	private String numeroParticao;

	@Column(name = "codigo_cliente")
	private Long clienteId;

}
