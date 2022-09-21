package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.sistemaf.domain.model.definition.Informacao;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="relato_atendimento")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class RelatoAtendimento extends Informacao {

	@ManyToOne
	@JoinColumn(name="codigo_atendimento")
	@JsonIdentityReference(alwaysAsId=true)
    @JsonBackReference
	private Atendimento atendimento;

}
