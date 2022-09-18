package com.sistemaf.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Table(name="verificacao_gravacoes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class VerificacaoGravacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Size(min=6, max=8)
	private String status;

	@Size(min=3 , max= 7)
	private String hd;

	@Column(name="qtd_gravacao")
	private int qtdGravacao;

	@Column(name="data_teste", nullable=false)
	@CreationTimestamp
	private OffsetDateTime dataTeste;

	@ManyToOne
	@JoinColumn(name="codigo_dvr")
	private Dvr dvr;

	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private Usuario usuario;


}
