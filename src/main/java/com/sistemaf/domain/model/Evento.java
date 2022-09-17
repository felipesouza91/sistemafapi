package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="evento")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="descricao_evento",nullable=false, unique=true)
	private String descricaoEvento;
	
	@NotNull
	@Size(max=10)
	@Column(name="tipo_protocolo",nullable=false)
	private String tipoProtocolo;
	
	@NotNull
	@Size(max=4)
	@Column(name="codigo_evento", nullable=false, unique=true)
	private String codigoEvento;
	
	@ManyToOne
	@JoinColumn(name="cod_ctrl_conf")
	private EventConfigure eventConfigure;

}
