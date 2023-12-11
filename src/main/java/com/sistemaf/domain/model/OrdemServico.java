package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="ordem_servico")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id", scope=OrdemServico.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name="codigo_service")
	private Integer codigoService;
	
	@Column(name="codigo_sigma")
	private Integer codigoSigma;

	@OneToOne
	@JoinColumn(name="codigo_motivo_os", nullable=false)
	private MotivoOs motivoOs;

	@Column(nullable=false)
	private String descricao;

	@Column(name="prioridade_os",nullable=false)
	private String prioridadeOs;
	
	@Column(nullable=false)
	private String solicitante;

	@OneToOne
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name="codigo_dvr")
	private Dvr dvr;

	@CreationTimestamp
	@Column(name="data_abertura",nullable=false)
	private OffsetDateTime dataAbertura;

	private Boolean fechado = false;

}
