package com.sistemaf.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="fechamento_os")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class FechamentoOs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name="motivo_fechamento",nullable=false)
	private String motivoFechamento;

	@CreationTimestamp
	@Column(name="data_fechamento",nullable=false)
	private OffsetDateTime dataFechamento;
	
	@Column(name="data_visita",nullable=false)
	private OffsetDateTime dataVisita;
	
	@Column(nullable=false)
	private String tecnico;
	
	@Column(name="observacao_servico")
	private String observcaoServico;

	@OneToOne
	@JoinColumn(name="codigo_os")
	private OrdemServico os;

}
