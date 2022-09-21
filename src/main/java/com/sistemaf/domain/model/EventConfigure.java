package com.sistemaf.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="event_configure")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class EventConfigure {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Size(max=20)
	@Column(name="tipo_evento", nullable=false)
	private String tipoEvento;
	
	@NotNull
	@Size(max=10)
	@Column(nullable=false)
	private String prioridade;
	
	@NotNull
	@Column(name="envia_monitoramento", nullable=false)
	private Boolean enviaMonitoramento;

}
