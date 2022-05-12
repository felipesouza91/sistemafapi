package com.sistemaf.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.infrastructure.util.customserializer.ClienteSerializer;
import com.sistemaf.infrastructure.util.customserializer.UsuarioSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="atendimento")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id", scope=Atendimento.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Column(name="descricao_problema")
	private String descricaoProblema;
	
	@Column(name="descricao_solucao")
	private String descricaoSolucao;
	
	@NotNull
	@Size(max=150)
	private String solicitante;
	
	@NotNull
	@ManyToOne
	@JsonSerialize(using=ClienteSerializer.class)
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull
	@Column(name="data_inicio")
	private LocalDateTime dataInicio;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="data_termino")
	private LocalDateTime dataTermino;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_usuario_inicio")
	@JsonSerialize(using =UsuarioSerializer.class)
	private Usuario usuarioInicio;

	@ManyToOne
	@JoinColumn(name="codigo_usuario_termino")
	@JsonSerialize(using =UsuarioSerializer.class)
	private Usuario usuarioTermino;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="atendimento")
    @JsonManagedReference
	private List<RelatoAtendimento> relatos;
	
	public Atendimento() {
		
	}

}
