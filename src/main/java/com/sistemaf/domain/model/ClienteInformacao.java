package com.sistemaf.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemaf.domain.model.definition.Informacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente_informacao")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteInformacao extends Informacao {

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="codigo_cliente")
	@JsonBackReference
	private Cliente cliente;

}
