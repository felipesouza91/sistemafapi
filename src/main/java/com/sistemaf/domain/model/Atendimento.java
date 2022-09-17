package com.sistemaf.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sistemaf.infrastructure.util.customserializer.ClienteSerializer;
import com.sistemaf.infrastructure.util.customserializer.UsuarioSerializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name="atendimento")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
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

	@NotNull
	@Column(name="data_inicio")
	private OffsetDateTime dataInicio;
	

	@Column(name="data_termino")
	private OffsetDateTime dataTermino;
	
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

}
