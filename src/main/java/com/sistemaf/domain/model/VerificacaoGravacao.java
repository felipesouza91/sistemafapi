package com.sistemaf.domain.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="verificacao_gravacoes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
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

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="data_teste", nullable=false)
	@CreatedDate
	private LocalDateTime dataTeste;

	@ManyToOne
	@JoinColumn(name="codigo_dvr")
	private Dvr dvr;

	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private Usuario usuario;
	
	public VerificacaoGravacao(Dvr dvr) { this.dvr = dvr; 	}
	public VerificacaoGravacao() {}

}
