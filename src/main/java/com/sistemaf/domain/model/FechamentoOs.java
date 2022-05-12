package com.sistemaf.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="fechamento_os")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FechamentoOs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name="motivo_fechamento",nullable=false)
	private String motivoFechamento;

	@CreationTimestamp
	@Column(name="data_fechamento",nullable=false)
	private LocalDateTime dataFechamento;
	
	@Column(name="data_visita",nullable=false)
	private LocalDateTime dataVisita;
	
	@Column(nullable=false)
	private String tecnico;
	
	@Column(name="observacao_servico")
	private String observcaoServico;

	@OneToOne
	@JoinColumn(name="codigo_os")
	private OrdemServico os;

}
