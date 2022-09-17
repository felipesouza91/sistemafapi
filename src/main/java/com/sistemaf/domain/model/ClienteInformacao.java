package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemaf.domain.model.definition.Informacao;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="cliente_informacao")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInformacao extends Informacao {

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="codigo_cliente")
	@JsonBackReference
	private Cliente cliente;

}
